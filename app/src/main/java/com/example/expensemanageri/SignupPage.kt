package com.example.expensemanageri

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity



class SignupPage : AppCompatActivity() {
    private lateinit var nameEditText: TextView
    private lateinit var emailEditText: TextView
    private lateinit var passwordEditText: TextView
    private lateinit var cpasswordEditText: TextView
    private lateinit var sharedPreferences: SharedPreferences
    val Name = "nameKey"
    val Email = "emailKey"
    val Pass = "passKey"
    val ConfirmPass = "ConfirmKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup_page)

        nameEditText = findViewById(R.id.name)
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.pass)
        cpasswordEditText = findViewById(R.id.cpass)
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val signupButton: Button = findViewById(R.id.butn)
        //clear the text input
        nameEditText.text = null
        emailEditText.text = null
        passwordEditText.text = null
        cpasswordEditText.text = null

        signupButton.setOnClickListener {
            signup()
        }
    }

    fun signup() {
        val name: String = nameEditText.text.toString()
        val email: String = emailEditText.text.toString()
        val password: String = passwordEditText.text.toString()
        val confirmPassword: String = cpasswordEditText.text.toString()

        val existingEmail = sharedPreferences.getString(Email, "")
        if (existingEmail == email) {
            // Email already exists, show toast message and return
            Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show()
            return
        }

        // Save user details in shared preferences
        val editor = sharedPreferences.edit()
        editor.putString(Name, name)
        editor.putString(Email, email)
        editor.putString(Pass, password)
        editor.putString(ConfirmPass, confirmPassword)
        editor.apply()

        // Redirect to login page
        val intent = Intent(this, LoginPage::class.java)
        startActivity(intent)
        finish()
    }


}

