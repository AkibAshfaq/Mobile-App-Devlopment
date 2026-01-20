package com.example.aiubportal

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class Library : AppCompatActivity() {
    lateinit var BookName : TextView
    lateinit var Category : TextView
    lateinit var Date : TextView
    lateinit var request : Button
    lateinit var returnbook : Button

    val database = FirebaseDatabase.getInstance()
    val databaseReference = database.reference.child("AvailableCourse")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_library)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var shareid = getSharedPreferences("AppData", MODE_PRIVATE)
        var studentId = shareid.getString("id", "").toString().trim()
        Toast.makeText(this, studentId, Toast.LENGTH_SHORT).show()

        Date = findViewById<TextView>(R.id.date)
        BookName = findViewById<TextView>(R.id.tvSelectedBook)
        Category = findViewById<TextView>(R.id.Book_Category)
        request = findViewById<Button>(R.id.btnPick)

        request.setOnClickListener {
            var Date = Date.text.toString().trim()
            var BookName = BookName.text.toString().trim()
            var Category = Category.text.toString().trim()

                databaseReference
                    .child(studentId)
                    .child("Request")
                    .child(Date).child(BookName).setValue(Category)
                    .addOnSuccessListener {
                        Toast.makeText(this,"Request Sent", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this,"Request Failed", Toast.LENGTH_SHORT).show()
                    }
        }

        returnbook = findViewById<Button>(R.id.btnDrop)

        returnbook.setOnClickListener {
            var Date = Date.text.toString().trim()
            var BookName = BookName.text.toString().trim()
            var Category = Category.text.toString().trim()

            databaseReference
                .child(studentId)
                .child("Request")
                .child(Date).setValue("Returned")
                .addOnSuccessListener {
                    Toast.makeText(this,"Request Sent", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this,"Request Failed", Toast.LENGTH_SHORT).show()
                }
        }




    }
}