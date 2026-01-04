package com.example.aiubportal

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class Homepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var Homebtn : ImageButton
        lateinit var Gradebtn : ImageButton
        lateinit var Noticebtn : ImageButton
        lateinit var Menubtn : ImageButton

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_homepage)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Function to call
        fun gotofragment(fragment: Fragment){
            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.fragmentview, fragment).commit()

        }

        Homebtn = findViewById<ImageButton>(R.id.Homebtn)
        Homebtn.setOnClickListener{
            gotofragment(HomeFragment())
        }

        Gradebtn = findViewById<ImageButton>(R.id.Gradebtn)
        Gradebtn.setOnClickListener{
            gotofragment(GradeFragment())
        }


        Noticebtn = findViewById<ImageButton>(R.id.Noticebtn)
        Noticebtn.setOnClickListener{
            gotofragment(NoticeFragment())
        }

        Menubtn = findViewById<ImageButton>(R.id.Menubtn)
        Menubtn.setOnClickListener {
            gotofragment(MenuFragment())
        }

    }
}