package com.example.expensemanageri

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class LoginPage : AppCompatActivity() {
    private lateinit var sharedpreferences: SharedPreferences
    private lateinit var email: TextView
    private lateinit var pass: TextView
    val mypreference = "mypref"
    val Email = "emailKey"
    val Pass = "passKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_page)

        email = findViewById(R.id.uEmail)
        pass = findViewById(R.id.uPass)
        val loginButton: Button = findViewById(R.id.butn)
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE)
        email.text = sharedpreferences.getString(Email, "")
        pass.text = sharedpreferences.getString(Pass, "")

        loginButton.setOnClickListener {
            login()
        }
    }

    fun login(){
        val e:String = email.text.toString()
        val p:String = pass.text.toString()
        val editor = sharedpreferences.edit()
        editor.putString(Email, e)
        editor.putString(Pass, p)
        editor.apply()

        if(!e.isNullOrEmpty() && !p.isNullOrEmpty()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            email.text = e
            pass.text = p
        }
    }
}