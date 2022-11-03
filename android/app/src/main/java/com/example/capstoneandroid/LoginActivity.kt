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

class LoginActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val goToSignup = findViewById<Button>(R.id.btn_goToSignup)
        val loginButton = findViewById<Button>(R.id.btn_login)
        val inputEmail = findViewById<EditText>(R.id.login_email)
        val inputPwd = findViewById<EditText>(R.id.login_pwd)
        auth = FirebaseAuth.getInstance()

        goToSignup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            auth?.signInWithEmailAndPassword(inputEmail.text.toString(),inputPwd.text.toString())
                ?.addOnCompleteListener {  //통신 완료가 된 후 무슨일을 할지
                        task ->
                    if (task.isSuccessful){
                        val intent = Intent(this@LoginActivity, selectDocsActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        // 오류가 난 경우!
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_home -> {
                val intent = Intent(this@LoginActivity, selectDocsActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_search -> {
                val intent = Intent(this@LoginActivity, MyFileActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_account -> {
                val intent = Intent(this@LoginActivity, LoginActivity::class.java)
                startActivity(intent)
                return true
            }
        }

        return false
    }
}
