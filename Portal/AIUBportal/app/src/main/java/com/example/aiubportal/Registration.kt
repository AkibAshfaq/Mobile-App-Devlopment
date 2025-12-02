package com.example.aiubportal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Registration : AppCompatActivity() {

    lateinit var home : Button
    lateinit var confirmreg : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        home = findViewById(R.id.tohome)
        home.setOnClickListener {
            var intent = Intent(this, Homepage::class.java)
            startActivity(intent)
        }

        confirmreg = findViewById(R.id.Donebtn)
        confirmreg.setOnClickListener {
            var intent = Intent(this, Homepage::class.java)
            startActivity(intent)
        }

    }
}