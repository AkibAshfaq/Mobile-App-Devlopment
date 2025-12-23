package com.example.medihope

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var callname: EditText
    lateinit var callpass: EditText
    lateinit var callbutton: Button
    lateinit var showbutton : Button

    lateinit var passviewbtn: TextView

    lateinit var nameviewbtn: TextView
    lateinit var tologin: Button
    var database: FirebaseDatabase = FirebaseDatabase.getInstance()

    var myRef: DatabaseReference = database.reference.child("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        callname = findViewById<EditText>(R.id.namebox)
        callpass = findViewById<EditText>(R.id.passbox)
        callbutton = findViewById<Button>(R.id.upload1)


        callbutton.setOnClickListener {
            val name = callname.text.toString().trim()
            val pass = callpass.text.toString().trim()

            if (name.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            myRef.child("Admin").child("Name").setValue(name)
            myRef.child("Admin").child("Pass").setValue(pass)
            Toast.makeText(this, "Data inserted", Toast.LENGTH_SHORT).show()
            callname.text.clear()
            callpass.text.clear()

        }

        nameviewbtn=findViewById<TextView>(R.id.namwview)
        passviewbtn=findViewById<TextView>(R.id.passview)
        showbutton = findViewById<Button>(R.id.see)

        showbutton.setOnClickListener {

            myRef.child("Admin").get().addOnSuccessListener {
                var name = it.child("Name").value
                nameviewbtn.text = name.toString()
                var pass = it.child("Pass").value
                passviewbtn.text = pass.toString()
            }.addOnFailureListener {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }

        }

        tologin = findViewById<Button>(R.id.login)

        tologin.setOnClickListener {
            val intent = Intent(this, Loginpage::class.java)
            startActivity(intent)
        }


    }
}