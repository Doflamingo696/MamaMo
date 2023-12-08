package com.example.mamamo

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Register : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var showPasswordButton: ImageButton
    private lateinit var db: mamamodb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_ui)

        db = mamamodb(this)

        usernameEditText = findViewById(R.id.username_edit_text)
        emailEditText = findViewById(R.id.email_edit_text)
        passwordEditText = findViewById(R.id.password_edit_text)
        registerButton = findViewById(R.id.register_button)
        showPasswordButton = findViewById(R.id.show_password_button)

        registerButton.setOnClickListener {
            val intent = Intent(this, HomeScreen::class.java)
            intent.putExtra("username", usernameEditText.text.toString())
            intent.putExtra("email", emailEditText.text.toString())
            intent.putExtra("password", passwordEditText.text.toString())
            registerUser(usernameEditText.text.toString(), emailEditText.text.toString(), passwordEditText.text.toString())
            startActivity(intent)
        }

        fun showPassword(view: View) {
            val passwordEditText = findViewById<EditText>(R.id.password_edit_text)
            val currentVisibility = passwordEditText.inputType
            if (passwordEditText.inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                passwordEditText.inputType = InputType.TYPE_CLASS_TEXT
            } else {
                passwordEditText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }

        showPasswordButton.setOnClickListener {
            val passwordEditText = findViewById<EditText>(R.id.password_edit_text)
            if (passwordEditText.inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                passwordEditText.inputType = InputType.TYPE_CLASS_TEXT
            } else {
                passwordEditText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }
    }

    private fun registerUser(toString: String, toString1: String, toString2: String) {
        val username = usernameEditText.text.toString()
        val email = emailEditText.text.toString()
        val pass = passwordEditText.text.toString()

        db.registerUser(username, email, pass)

        Toast.makeText(this, "Registered!", Toast.LENGTH_SHORT).show()
    }
}