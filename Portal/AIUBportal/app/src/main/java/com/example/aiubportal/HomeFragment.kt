package com.example.aiubportal

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.values
import java.time.LocalDate
import java.time.MonthDay
import java.time.format.TextStyle
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    lateinit var sundayroution : TextView
    lateinit var  mondayroutin: TextView
    lateinit var  turesdayroutin: TextView
    lateinit var  wednesdaydayroutin: TextView
    lateinit var  thrusdayyroutin: TextView
    lateinit var day : TextView

    lateinit var ad_drp : Button
    lateinit var reg : Button


    val database = FirebaseDatabase.getInstance()
    val databaseReference = database.reference.child("AvailableCourse")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun getCurrentWeekday(): String {
        val today = LocalDate.now()
        val dayOfWeek = today.dayOfWeek

        // Returns the full name of the weekday based on your device's locale
        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sundayroution = view.findViewById<TextView>(R.id.SundayCourse1)
        mondayroutin = view.findViewById<TextView>(R.id.MondayCourse1)
        turesdayroutin = view.findViewById<TextView>(R.id.TuesdayCourse1)
        wednesdaydayroutin = view.findViewById<TextView>(R.id.wednesdayCourse1)
        thrusdayyroutin = view.findViewById<TextView>(R.id.Thursdaycourse1)
        day= view.findViewById<TextView>(R.id.Weekdayview)

        var shareid = requireContext().getSharedPreferences("AppData", MODE_PRIVATE)
        var studentId = shareid.getString("id", "").toString().trim()
        Toast.makeText(requireContext(), studentId, Toast.LENGTH_SHORT).show()


        databaseReference.child("22-48928-3").child("Fall25-26").child("Name").get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    sundayroution.text = snapshot.child("Course1").value?.toString() ?: "No Classes"
                    mondayroutin.text = snapshot.child("Course2").value?.toString() ?: "No Classes"
                    turesdayroutin.text = snapshot.child("Course3").value?.toString() ?: "No Classes"
                    wednesdaydayroutin.text = snapshot.child("Course4").value?.toString() ?: "No Classes"
                    thrusdayyroutin.text = "No Classes"
                } else {
                    setNoClassesToAll()
                    Toast.makeText(requireContext(), "No course data found for this ID.", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                setNoClassesToAll()
                Toast.makeText(requireContext(), "Failed to retrieve data.", Toast.LENGTH_SHORT).show()
            }



        day.text = getCurrentWeekday().toString()

        ad_drp = view.findViewById(R.id.Addordropbtn)

        reg = view.findViewById(R.id.Gotoregistrationbtn)

        ad_drp.setOnClickListener {

            val intent = Intent(requireContext(), Adddrop::class.java)
            startActivity(intent)

        }

        reg.setOnClickListener {
            val intent = Intent(requireContext(), Registration::class.java)
            startActivity(intent)
        }


    }

    private fun setNoClassesToAll() {
        sundayroution.text = "No Classes"
        mondayroutin.text = "No Classes"
        turesdayroutin.text = "No Classes"
        wednesdaydayroutin.text = "No Classes"
        thrusdayyroutin.text = "No Classes"
    }

}