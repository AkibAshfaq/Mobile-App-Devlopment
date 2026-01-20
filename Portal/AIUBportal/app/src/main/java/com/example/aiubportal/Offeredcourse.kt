package com.example.aiubportal

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class Offeredcourse : AppCompatActivity() {


    lateinit var offeredCourseContainer : ScrollView

    val db = FirebaseDatabase.getInstance()
    val reff = db.getReference("OfferedCourses")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_offeredcourse)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

            offeredCourseContainer = findViewById<ScrollView>(R.id.Container)

        reff.orderByKey()
            .get()
            .addOnSuccessListener { snapshot ->

                offeredCourseContainer.removeAllViews()

                for (course in snapshot.children) {

                    val courseCode = course.key ?: continue
                    val courseName = course.value?.toString() ?: ""

                    addOfferedCourseRow(courseCode, courseName)
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to fetch data", Toast.LENGTH_SHORT).show()
            }

    }

    fun addOfferedCourseRow(code: String, name: String) {

        val rowLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            setPadding(16, 16, 16, 16)
            background = ContextCompat.getDrawable(
                this@Offeredcourse,
                android.R.drawable.editbox_background
            )
        }

        // Course text
        val textView = TextView(this).apply {
            text = "$code : $name"
            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
            textSize = 16f
            setTextColor(Color.BLACK)
        }

        // Dropdown icon (ABC)
        val dropIcon = ImageView(this).apply {
            setImageResource(android.R.drawable.arrow_down_float)
            layoutParams = LinearLayout.LayoutParams(60, 60)
        }

        rowLayout.addView(textView)
        rowLayout.addView(dropIcon)

        offeredCourseContainer.addView(rowLayout)

        rowLayout.setOnClickListener {
            Toast.makeText(this, "$code selected", Toast.LENGTH_SHORT).show()
        }
    }


}