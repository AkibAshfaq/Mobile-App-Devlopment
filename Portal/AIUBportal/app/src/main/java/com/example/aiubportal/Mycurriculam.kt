package com.example.aiubportal

import android.R.attr.orientation
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class Mycurriculam : AppCompatActivity() {
    lateinit var btn : Button
    lateinit var gradContainer : LinearLayout
    var db = FirebaseDatabase.getInstance()
    var ref = db.reference.child("Curriculum")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mycurriculam)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        gradContainer = findViewById<LinearLayout>(R.id.gradContainer)
        val selectedCourses = mutableListOf<String>()

        ref
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
                            1000
                        )
                        setPadding(12, 12, 12, 12)
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
                    for(v in 1..4) {

                        val check = CheckBox(this).apply {
                            setOnClickListener {
                                if(isChecked)
                                    if(courseName !in selectedCourses){
                                        selectedCourses.add(courseName)
                                    }else{
                                        isChecked = false
                                    }
                                else
                                    selectedCourses.remove(courseName)
                            }
                        }

                        rowLayout.addView(check)

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
                    }

                    gradContainer.addView(rowLayout)
                }
            }

        btn = findViewById<Button>(R.id.backbtn)
        btn.setOnClickListener {
            finish()
        }
    }

}