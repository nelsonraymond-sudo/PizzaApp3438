package com.example.pizzaapp3438

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_account)
        val dial: LinearLayout = findViewById(R.id.linearLayout)
        dial.setOnClickListener {
            val dialIntent: Intent = Uri.parse("tel:085691996166").let { number ->
                Intent(Intent.ACTION_DIAL, number)
            }
            startActivity(dialIntent)
        }

        val website:LinearLayout = findViewById(R.id.linearLayout)
        website.setOnClickListener {
            val webIntent:Intent = Uri.parse("https://home.amikom.ac.id/").let { webpage ->
                Intent(Intent.ACTION_VIEW, webpage)
            }
            startActivity(webIntent)
        }
        //call maps activity
        val maps:LinearLayout = findViewById(R.id.linearLayout)
        maps.setOnClickListener {
            val mapIntent:Intent = Uri.parse("geo:47.6, -122.3?z=11").let { gmaps ->
                Intent(Intent.ACTION_VIEW, gmaps)
            }
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        //set data
        val txtNama: EditText = findViewById(R.id.editTextName)
        val txtEmail: EditText = findViewById(R.id.editTextEmail)
        val txtPassword: EditText = findViewById(R.id.editTextPassword)
        val txtLevel: EditText = findViewById(R.id.editTextLevel)

        txtNama.setText(LoginActivity.name)
        txtEmail.setText(LoginActivity.email)
        txtPassword.setText(LoginActivity.password)
        txtLevel.setText(LoginActivity.level)

    }
}