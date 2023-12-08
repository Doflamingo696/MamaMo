package com.example.mamamo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ActivityAddCannedFood : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var cannedFoodName: EditText
    private lateinit var expiryDate: EditText
    private lateinit var uploadButton: Button
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_canned_food)

        imageView = findViewById(R.id.add_logo)
        cannedFoodName = findViewById(R.id.canned_food_name)
        uploadButton = findViewById(R.id.upload_button)
        saveButton = findViewById(R.id.save_button)
        expiryDate = findViewById(R.id.expiry_date)


        uploadButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1000)
        }

        saveButton.setOnClickListener {
            val cfoodName = cannedFoodName.text.toString()
            if (cfoodName.isEmpty()) {
                Toast.makeText(this, "Please enter canned food name", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "$cfoodName added successfully!", Toast.LENGTH_SHORT).show()
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