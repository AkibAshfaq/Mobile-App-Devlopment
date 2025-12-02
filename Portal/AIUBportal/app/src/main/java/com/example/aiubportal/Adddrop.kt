package com.example.aiubportal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Adddrop : AppCompatActivity() {

    lateinit var home : Button
    lateinit var addordrop : Button
    lateinit var drop : Button
    lateinit var  msg : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_adddrop)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        home = findViewById(R.id.Tohomebtn)
        home.setOnClickListener {
            var intent = Intent(this, Homepage::class.java)
            startActivity(intent)
        }

        addordrop = findViewById(R.id.Addbtn)
        addordrop.setOnClickListener {
            var intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }

        drop = findViewById(R.id.dropbtn)
        msg = findViewById(R.id.dropmessege)
        drop.setOnClickListener {
            msg.text = "Drop Successfully"
        }
    }
}