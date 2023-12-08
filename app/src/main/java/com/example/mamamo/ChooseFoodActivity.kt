package com.example.mamamo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ChooseFoodActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var fruitButton: Button
    private lateinit var vegetableButton: Button
    private lateinit var cannedfoodButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_food)

        imageView = findViewById(R.id.add_logo)
        fruitButton = findViewById(R.id.fruit_button)
        vegetableButton = findViewById(R.id.vegetable_button)
        cannedfoodButton = findViewById(R.id.canned_food_button)

        fruitButton.setOnClickListener {
            val intent = Intent(this, AddFruitActivity::class.java)
            startActivity(intent)
        }

        vegetableButton.setOnClickListener {
            val intent = Intent(this, AddVegetableActivity::class.java)
            startActivity(intent)
        }

        cannedfoodButton.setOnClickListener {
            val intent = Intent(this, ActivityAddCannedFood::class.java)
            startActivity(intent)
        }
    }
}
