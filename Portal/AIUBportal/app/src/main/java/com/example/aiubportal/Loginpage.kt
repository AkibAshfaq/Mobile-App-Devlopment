package com.example.aiubportal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class Loginpage : AppCompatActivity() {

    lateinit var id: TextView
    lateinit var pass: TextView
    lateinit var  login : Button

    var fireauth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loginpage)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        login = findViewById<Button>(R.id.LoginBtn)
        id = findViewById<TextView>(R.id.LoginStudentId)
        pass = findViewById<TextView>(R.id.LoginStudentPassword)

        login.setOnClickListener {

            var idtext = id.text.toString().trim()
            var passtext = pass.text.toString().trim()
//            if(idtext.isEmpty() || passtext.isEmpty()){
//                Toast.makeText(this,"Feild needs to be filled", Toast.LENGTH_SHORT).show()
//            }
//            else{
//                var idregx = Regex("^\\d{2}-\\d{5}-\\d$")
//                if(idregx.matches(idtext)){
//                    fireauth.signInWithEmailAndPassword(idtext + "@student.aiub.edu",passtext).addOnSuccessListener {
////                        var shareid = getSharedPreferences("AppData", MODE_PRIVATE)
////                        shareid.edit().putString("id",idtext).apply()
//                        Toast.makeText(this,"Login Successful", Toast.LENGTH_SHORT).show()
//                        var intent = Intent(this, Homepage::class.java)
//                        startActivity(intent)
//                    }.addOnFailureListener {
//                        Toast.makeText(this,"Not Registered", Toast.LENGTH_SHORT).show()
//                    }
//                }else{
//                    Toast.makeText(this,"Invalid ID", Toast.LENGTH_SHORT).show()
//                }
//            }
            var shareid = getSharedPreferences("AppData", MODE_PRIVATE)
            shareid.edit().putString("id","22-48928-3").apply()
            var intent = Intent(this, Homepage::class.java)
            startActivity(intent)
        }

    }
}