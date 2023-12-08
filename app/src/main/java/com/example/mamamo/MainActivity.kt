package com.example.mamamo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mamamo.databinding.ActivityMainBinding
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var loginButton: Button
    private lateinit var username: EditText
    private lateinit var pass: EditText
    private lateinit var db: mamamodb

    fun showPassword(view: View) {
        val passwordEditText = findViewById<EditText>(R.id.password_edit_text)
        val currentVisibility = passwordEditText.inputType

        if (currentVisibility == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
            passwordEditText.inputType = InputType.TYPE_CLASS_TEXT
            passwordEditText.transformationMethod = PasswordTransformationMethod()
        } else {
            passwordEditText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
            passwordEditText.transformationMethod = HideReturnsTransformationMethod()
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = mamamodb(this)

        loginButton = findViewById(R.id.login_button)
        username = findViewById(R.id.username_edit_text)
        pass = findViewById(R.id.password_edit_text)

        val registerTextView: TextView = findViewById(R.id.register_text_view)
        registerTextView.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener {intent.putExtra("password", pass.text.toString())
            val intent = Intent(this, HomeScreen::class.java)
            intent.putExtra("username", username.text.toString())


            loginUser(binding.usernameEditText.text.toString(), binding.passwordEditText.text.toString())
            startActivity(intent)
        }

    }
    private fun loginUser(username: String, password: String) {
        val username = binding.usernameEditText.text.toString()
        val pass = binding.passwordEditText.text.toString()

        db.loginUser(username, pass)
        Toast.makeText(this, "Logged In! Welcome $username!", Toast.LENGTH_SHORT).show()
    }
}