package com.example.aiubportal

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class Dropapplications : AppCompatActivity() {
    lateinit var course : EditText
    lateinit var section : EditText
    lateinit var  reason : EditText
    lateinit var  request : Button
    lateinit var back : Button
    val db = FirebaseDatabase.getInstance()
    val ref = db.reference.child("Drop")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dropapplications)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var shareid = getSharedPreferences("AppData", MODE_PRIVATE)
        var studentId = shareid.getString("id", "").toString().trim()
        studentId = "22-48928-3"

        course = findViewById<EditText>(R.id.Course)
        section = findViewById<EditText>(R.id.Section)
        reason = findViewById<EditText>(R.id.Reason)
        request = findViewById<Button>(R.id.request)

        request.setOnClickListener {
            val cour = course.text.toString()
            val sec = section.text.toString()
            val reas = reason.text.toString()

            if(cour.isEmpty()){
                Toast.makeText(this, "Course Feild Required", Toast.LENGTH_SHORT).show()
            }else if(sec.isEmpty()){
                Toast.makeText(this, "Section Feild Required", Toast.LENGTH_SHORT).show()
            }else if(reas.isEmpty()){
                Toast.makeText(this, "Reason Feild Required", Toast.LENGTH_SHORT).show()
            }else{
                ref.child(studentId).child(cour).child(sec).setValue(reas).addOnSuccessListener {
                    Toast.makeText(this, "Request Sent", Toast.LENGTH_SHORT).show()
                    course.text.clear()
                    section.text.clear()
                    reason.text.clear()
                }.addOnFailureListener {
                    Toast.makeText(this, "Request is not accepted now", Toast.LENGTH_SHORT).show()
                }
            }
        }

        back = findViewById<Button>(R.id.Backbtn)

        back.setOnClickListener {
            finish()
        }
    }
}