package com.example.mamamo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import org.w3c.dom.Text


class HomeScreen : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var addButton: ImageButton
    private lateinit var menuBar: ImageView
    private lateinit var searchView: SearchView
    private lateinit var navigationView: NavigationView
    private lateinit var icon: ImageView
    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var db: mamamodb

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        val navView = findViewById<NavigationView>(R.id.nav_view)
        val headerView = navView.getHeaderView(0)

        val db = mamamodb(this)

        drawerLayout = findViewById(R.id.drawer_layout)
        addButton = findViewById(R.id.add_button)
        menuBar = findViewById(R.id.menubar)
        navigationView = findViewById(R.id.nav_view)
        icon = findViewById(R.id.logo)
        searchView = findViewById(R.id.search_bar)

        addButton.setOnClickListener {
            val intent = Intent(this, ChooseFoodActivity::class.java)
            startActivity(intent)
        }

        menuBar.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                icon.visibility = View.GONE
            } else {
                icon.visibility = View.VISIBLE
            }
        }

        searchView.isIconified = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Code to execute search
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Code to filter search results
                return false
            }
        })
    }
}


