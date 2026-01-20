package com.example.aiubportal

import android.content.Intent
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
import java.util.jar.Attributes

class Adddrop : AppCompatActivity() {

    lateinit var home : Button
    lateinit var addordrop : Button
    lateinit var gradContainer : LinearLayout
    val database = FirebaseDatabase.getInstance()
    val databaseReference = database.reference.child("AvailableCourse")

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

        var shareid = this.getSharedPreferences("AppData", MODE_PRIVATE)
        var studentId = shareid.getString("id", "").toString().trim()

        gradContainer = findViewById<LinearLayout>(R.id.gradContainer)


        databaseReference
            .child(studentId)
            .child("Fall25-26")
            .get()
            .addOnSuccessListener { snapshot ->
                gradContainer.removeAllViews()
                for ( v in snapshot.child("Name").children) {
                    var coursekey = v.key.toString()
                    var courseName = v.value.toString()
                    val rowLayout = LinearLayout(this).apply {
                        orientation = LinearLayout.VERTICAL
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            500
                        )
                        setPadding(12, 12, 12, 12)
                    }

                    val textView1 = TextView(this).apply {
                        text = courseName
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            80,
                            1f)
                        textSize = 18f
                    }

                    val textView2 = TextView(this).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            120)
                        textSize = 14f
                        setTextColor(Color.RED)
                        setPadding(10,10,10,10)
                    }

                    var dropbtn = Button(this).apply {
                        text = "Drop"
                        layoutParams = LinearLayout.LayoutParams(
                            150,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        setBackgroundColor(Color.RED)
                        setOnClickListener {
                            databaseReference.child(studentId).child("Fall25-26").child("Name").child(coursekey).removeValue().addOnSuccessListener {
                                textView2.text = "Drop Successfully"
                            }
                        }
                    }

                    rowLayout.addView(textView1)
                    rowLayout.addView(dropbtn)
                    rowLayout.addView(textView2)

                    gradContainer.addView(rowLayout)
                }
            }

    }


}