package com.example.capstoneandroid

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AccountActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener  {

    var auth: FirebaseAuth? = null
    private var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val myAccount = findViewById<TextView>(R.id.text_myAccount)
        val logoutButton = findViewById<Button>(R.id.btn_logout)
        auth = FirebaseAuth.getInstance()
        user = auth!!.currentUser

        if (user != null) {
            myAccount.text = user!!.email + " 님"
        }

        logoutButton.setOnClickListener {
            auth!!.signOut()
            val intent = Intent(this@AccountActivity, selectDocsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_home -> {
                val intent = Intent(this@AccountActivity, selectDocsActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_search -> {
                return true
            }
            R.id.action_account -> {
                val intent = Intent(this@AccountActivity, LoginActivity::class.java)
                startActivity(intent)
                return true
            }
        }

        return false
    }
}