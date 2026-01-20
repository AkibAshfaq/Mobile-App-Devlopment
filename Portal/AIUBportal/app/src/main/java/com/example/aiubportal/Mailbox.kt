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

class Mailbox : AppCompatActivity() {

    lateinit var mail : EditText
    lateinit var  sentbtn : Button
    lateinit var  recipent : EditText

    lateinit var  back : Button
    val Db = FirebaseDatabase.getInstance()
    val Ref = Db.reference.child("Mail")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mailbox)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var shareid = getSharedPreferences("AppData", MODE_PRIVATE)
        var studentId = shareid.getString("id", "").toString().trim()
        studentId = "22-48928-3"

        mail = findViewById<EditText>(R.id.mail)
        sentbtn = findViewById<Button>(R.id.Sent)
        recipent= findViewById<EditText>(R.id.Recipent)

        sentbtn.setOnClickListener {
            val mailtext = mail.text.toString()
            val recitext = recipent.text.toString()
            if(mailtext.isEmpty()){
                Toast.makeText(this, "Mail Feild Required", Toast.LENGTH_SHORT).show()
            }else if(recitext.isEmpty()){
                Toast.makeText(this, "Recipent Feild Required", Toast.LENGTH_SHORT).show()
            }else{
                Ref.child(recitext).child(studentId).setValue(mailtext).addOnSuccessListener {
                    Toast.makeText(this, "Mail Sent", Toast.LENGTH_SHORT).show()
                    mail.text.clear()
                    recipent.text.clear()
                }.addOnFailureListener {
                    Toast.makeText(this, "Mail Not Sent", Toast.LENGTH_SHORT).show()
                }
            }
        }

        back=findViewById<Button>(R.id.Backtohome)
        back.setOnClickListener {
            finish()
        }
    }
}