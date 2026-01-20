package com.example.aiubportal

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class Registrationview : AppCompatActivity() {
    lateinit var gradContainer : LinearLayout
    lateinit var back : Button
    val database = FirebaseDatabase.getInstance()
    val databaseReference = database.reference.child("AvailableCourse")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrationview)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var shareid = getSharedPreferences("AppData", MODE_PRIVATE)
        var studentId = shareid.getString("id", "").toString().trim()

        gradContainer = findViewById<LinearLayout>(R.id.gradContainer)


        databaseReference
            .child(studentId)
            .child("Fall25-26")
            .get()
            .addOnSuccessListener { snapshot ->
                gradContainer.removeAllViews()
                var i = 0
                for ( v in snapshot.child("Name").children) {
                    i = i+1
                    val courseName = snapshot.child("Name").child("Course$i").value?.toString() ?: "N/A"
                    val marks = snapshot.child("Credits").child("Course$i").value?.toString() ?: "-"
                    val rowLayout = LinearLayout(this).apply {
                        orientation = LinearLayout.HORIZONTAL
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            120
                        )
                        setPadding(12, 12, 12, 12)
                    }

                    val textView1 = TextView(this).apply {
                        text = courseName
                        layoutParams = LinearLayout.LayoutParams(270,
                            120,
                            1f)
                        textSize = 18f
                    }

                    val textView2 = TextView(this).apply {
                        text = marks + " Credits"
                        layoutParams = LinearLayout.LayoutParams(270,
                            120)
                        gravity = Gravity.CENTER
                        setBackgroundColor(Color.parseColor("#A7D5F0"))
                    }

                    rowLayout.addView(textView1)
                    rowLayout.addView(textView2)
                    gradContainer.addView(rowLayout)
                }
            }

        back=findViewById<Button>(R.id.backbtn)

        back.setOnClickListener {
            finish()
        }

    }
}