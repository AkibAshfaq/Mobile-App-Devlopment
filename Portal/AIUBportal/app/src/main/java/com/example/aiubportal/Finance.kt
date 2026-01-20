package com.example.aiubportal

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class Finance : AppCompatActivity() {
    lateinit var currentdue : TextView
    lateinit var semester : TextView
    lateinit var Tuitionfee : TextView
    lateinit var totalpaid : TextView
    lateinit var currentdue2 : TextView
    lateinit var back : Button

    val db = FirebaseDatabase.getInstance()
    val dbReference = db.reference.child("Finance")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_finance)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var shareid = getSharedPreferences("AppData", MODE_PRIVATE)
        var studentId = shareid.getString("id", "").toString().trim()

        totalpaid=findViewById<TextView>(R.id.totalpaid)
        currentdue=findViewById<TextView>(R.id.currentdue)
        currentdue2=findViewById<TextView>(R.id.currentdue2)
        Tuitionfee=findViewById<TextView>(R.id.Tuitionfee)
        semester=findViewById<TextView>(R.id.finalcetitle)

        dbReference.child(studentId).get().addOnSuccessListener {
            Datasnapshot ->
            if(Datasnapshot.exists()){
                totalpaid.text="BDT " + Datasnapshot.child("TotalPaid").value.toString()
                currentdue.text="BDT " + Datasnapshot.child("Due").value.toString()
                currentdue2.text="BDT " + Datasnapshot.child("Due").value.toString()
                Tuitionfee.text="BDT " + Datasnapshot.child("TuitionFee").value.toString()
                semester.text="Financial Details (" + Datasnapshot.child("Semester").value.toString() + ")"
            }else{
                Toast.makeText(this,"No Data Found", Toast.LENGTH_SHORT).show()
            }
        }

        back=findViewById<Button>(R.id.Backtohome)
        back.setOnClickListener {
            finish()
        }

    }
}