package com.example.expensemanageri

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_screen)

        // Get references to the buttons
        val loginButton: Button = findViewById(R.id.loginBtn)
        val signUpButton: Button = findViewById(R.id.signBtn)

        // Set click listeners for the buttons
        loginButton.setOnClickListener {
            // Create Intent to open LoginPage activity
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
        }

        signUpButton.setOnClickListener {
            // Create Intent to open SignupPage activity
            val intent = Intent(this, SignupPage::class.java)
            startActivity(intent)
        }
    }
}