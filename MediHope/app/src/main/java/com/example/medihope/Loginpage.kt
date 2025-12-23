package com.example.medihope

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class Loginpage : AppCompatActivity() {

    lateinit var callname: EditText
    lateinit var callpass: EditText
    lateinit var callbutton: Button
    var fireauth : FirebaseAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loginpage)
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

            fireauth.createUserWithEmailAndPassword(name,pass).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
                }
            }

            callname.text.clear()
            callpass.text.clear()
        }

    }
}