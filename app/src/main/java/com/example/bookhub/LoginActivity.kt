package com.example.bookhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.bookhub.activity.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.registration.*

class LoginActivity : AppCompatActivity() {

    lateinit var handler:DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        handler = DatabaseHelper(this)
        showHome()
        registration.setOnClickListener {
            showRegistration() }

        login.setOnClickListener {
            showLogin() }

        submit.setOnClickListener {
            handler.insertUserData(
                name.text.toString(),
                email.text.toString(),
                pass.text.toString()
            )
            showHome()
        }

        login_button.setOnClickListener {
            if (handler.userPresent(login_email.text.toString(), login_pass.text.toString())) {
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else
                Toast.makeText(this, "Invalid User", Toast.LENGTH_LONG).show()
        }
    }
    private fun showRegistration(){
        regLayout.visibility=View.VISIBLE
        login_layout.visibility=View.GONE
        home.visibility=View.GONE


    }

    private fun showLogin(){
        regLayout.visibility=View.GONE
        login_layout.visibility=View.VISIBLE
        home.visibility=View.GONE
    }
    private fun showHome(){
        regLayout.visibility=View.GONE
        login_layout.visibility=View.GONE
        home.visibility=View.VISIBLE
    }
}
