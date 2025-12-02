package com.example.aiubportal

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Noticeview : AppCompatActivity() {

    lateinit var view : WebView

    lateinit var back : ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_noticeview)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        view = findViewById<WebView>(R.id.noticeviewpanel)


        view.settings.cacheMode = WebSettings.LOAD_NO_CACHE

        view.clearCache(true)
        view.settings.javaScriptEnabled = true
        view.settings.domStorageEnabled = true

        view.loadUrl("https://www.aiub.edu/final-term-exam-permit-for-fall-2025-26-for-llb-and-bpharm-students")

        back = findViewById<ImageButton>(R.id.backtonotice)

        back.setOnClickListener {
            finish()
        }



    }
}