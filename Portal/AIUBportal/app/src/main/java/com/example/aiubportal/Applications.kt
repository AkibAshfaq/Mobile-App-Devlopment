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

class Applications : AppCompatActivity() {
    lateinit var mailtext : EditText
    lateinit var  sentbtn : Button
    lateinit var back : Button
    val Db = FirebaseDatabase.getInstance()
    val Ref = Db.reference.child("Applications")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_applications)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var shareid = getSharedPreferences("AppData", MODE_PRIVATE)
        var studentId = shareid.getString("id", "").toString().trim()

        mailtext = findViewById<EditText>(R.id.mail)
        sentbtn = findViewById<Button>(R.id.Sent)

        sentbtn.setOnClickListener {
            val mail = mailtext.text.toString()
            if (!mail.isNotEmpty()) {
                Toast.makeText(this, "Mail Feild Required", Toast.LENGTH_SHORT).show()
            }else{
                Ref.child(studentId).setValue(mail).addOnSuccessListener {
                    Toast.makeText(this, "Mail Sent", Toast.LENGTH_SHORT).show()
                    mailtext.text.clear()
                }.addOnFailureListener {
                    Toast.makeText(this, "Mail Not Sent", Toast.LENGTH_SHORT).show()
                }

            }
        }

        back = findViewById<Button>(R.id.Backbutton)

        back.setOnClickListener {
            finish()
        }

    }
}