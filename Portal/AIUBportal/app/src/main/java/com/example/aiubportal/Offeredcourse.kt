package com.example.aiubportal

import android.graphics.Color
import android.os.Bundle
import android.print.PrintAttributes
import android.view.Gravity
import android.widget.CheckBox
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

    lateinit var gradContainer : LinearLayout
    val database = FirebaseDatabase.getInstance()
    val databaseReference = database.reference.child("Offered Course")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_offeredcourse)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var shareid = getSharedPreferences("AppData", MODE_PRIVATE)
        var studentId = shareid.getString("id", "").toString().trim()

        gradContainer = findViewById<LinearLayout>(R.id.gradContainer)


        databaseReference
        databaseReference
            .get()
            .addOnSuccessListener { snapshot ->
                gradContainer.removeAllViews()
                for ( v in snapshot.children) {
                    var coursekey = v.key.toString()
                    var courseName = v.value.toString()
                    val rowLayout = LinearLayout(this).apply {
                        orientation = LinearLayout.VERTICAL
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            250
                        )
                        setPadding(20, 20, 20, 20)

                    }

                    val textView2 = TextView(this).apply {
                        text= courseName
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            120
                        )
                        textSize = 24f
                        setTextColor(Color.BLUE)
                    }
                    rowLayout.addView(textView2)

                    val textView1 = TextView(this).apply {
                        text = coursekey + " " + courseName
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            150,
                            1f
                        )
                        textSize = 18f
                    }

                    rowLayout.addView(textView1)


                    gradContainer.addView(rowLayout)
                }
            }


    }



}