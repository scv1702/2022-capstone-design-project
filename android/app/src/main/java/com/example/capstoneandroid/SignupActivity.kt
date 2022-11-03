package com.example.capstoneandroid

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val goToLogin = findViewById<Button>(R.id.btn_goToLogin)
        val signupButton = findViewById<Button>(R.id.btn_signup)
        val inputEmail = findViewById<EditText>(R.id.signup_email)
        val inputPwd = findViewById<EditText>(R.id.signup_pwd)
        auth = FirebaseAuth.getInstance()

        goToLogin.setOnClickListener {
            val intent = Intent(this@SignupActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        signupButton.setOnClickListener {
            auth?.createUserWithEmailAndPassword(inputEmail.text.toString(),inputPwd.text.toString())
                ?.addOnCompleteListener {
                        task ->
                    if (task.isSuccessful){
                        val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_home -> {
                val intent = Intent(this@SignupActivity, selectDocsActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_search -> {
                val intent = Intent(this@SignupActivity, MyFileActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_account -> {
                val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                startActivity(intent)
                return true
            }
        }

        return false
    }
}