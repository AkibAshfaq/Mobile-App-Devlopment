package com.example.medihope

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    lateinit var callname: EditText
    lateinit var callpass: EditText
    lateinit var callbutton: Button

    lateinit var showname : TextView

    lateinit var showpass : TextView

    lateinit var showbutton : Button

    lateinit var passviewbtn: TextView

    lateinit var nameviewbtn: TextView
    var database: FirebaseDatabase = FirebaseDatabase.getInstance()

    var myRef: DatabaseReference = database.reference.child("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        callname = findViewById<EditText>(R.id.namebox)
        callpass = findViewById<EditText>(R.id.passbox)
        callbutton = findViewById<Button>(R.id.upload)
        showname = findViewById<TextView>(R.id.Name)
        showpass = findViewById<TextView>(R.id.Pass)


        callbutton.setOnClickListener {
            val name = callname.text.toString().trim()
            val pass = callpass.text.toString().trim()

            if (name.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            myRef.child("Admin").child("Name").setValue(name)
            myRef.child("Admin").child("Pass").setValue(pass)
            Toast.makeText(this, "Data inserted", Toast.LENGTH_SHORT).show()
            callname.text.clear()
            callpass.text.clear()



//            val userData = mapOf(
//                "Name" to name,
//                "Pass" to pass
//            )
//
//            myRef.child("Admin2").addValueEventListener(object : ValueEventListener{
//
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    var name = snapshot.child("Name").value
//                    showname.text = name.toString()
//                    var pass = snapshot.child("Pass").value
//                    showname.text = pass.toString()
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    TODO("Not yet implemented")
//                }
//            })

        }

        nameviewbtn=findViewById<TextView>(R.id.namwview)
        passviewbtn=findViewById<TextView>(R.id.passview)
        showbutton = findViewById<Button>(R.id.see)

        showbutton.setOnClickListener {

            myRef.child("Admin").get().addOnSuccessListener {
                var name = it.child("Name").value
                nameviewbtn.text = name.toString()
                var pass = it.child("Pass").value
                passviewbtn.text = pass.toString()
            }.addOnFailureListener {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }

        }


    }
}