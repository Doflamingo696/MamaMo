package com.example.mamamo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddFruitActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var editText: EditText
    private lateinit var uploadButton: Button
    private lateinit var saveButton: Button
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_fruit)

        imageView = findViewById(R.id.add_logo)
        editText = findViewById(R.id.fruit_name)
        uploadButton = findViewById(R.id.upload_button)
        saveButton = findViewById(R.id.save_button)


        uploadButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1000)
        }

        saveButton.setOnClickListener {
            val fruitName = editText.text.toString()
            if (fruitName.isEmpty()) {
                Toast.makeText(this, "Please enter fruit name", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "$fruitName added successfully!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000 && resultCode == RESULT_OK && data != null) {
            val selectedImage = data.data
            imageView.setImageURI(selectedImage)
        }
    }
}