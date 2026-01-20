package com.example.aiubportal

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.database.FirebaseDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GradeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GradeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var gradContainer : LinearLayout
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
        return inflater.inflate(R.layout.fragment_grade, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var shareid = requireContext().getSharedPreferences("AppData", MODE_PRIVATE)
        var studentId = shareid.getString("id", "").toString().trim()

        gradContainer = view.findViewById<LinearLayout>(R.id.gradContainer)


        databaseReference
            .child(studentId)
            .child("Fall25-26")
            .get()
            .addOnSuccessListener { snapshot ->
                gradContainer.removeAllViews()
                var i = 0
                for ( v in snapshot.child("Name").children) {
                    i = i+1
                    val courseName = snapshot.child("Name").child("Course$i").value?.toString() ?: "N/A"
                    val marks = snapshot.child("Marks").child("Course$i").value?.toString() ?: "-"
                    val rowLayout = LinearLayout(requireContext()).apply {
                        orientation = LinearLayout.HORIZONTAL
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            120
                        )
                        setPadding(12, 12, 12, 12)
                        setOnClickListener {
                            val intent = Intent(requireContext(), Gradebook::class.java)
                            startActivity(intent)
                        }
                    }

                    val textView1 = TextView(requireContext()).apply {
                        text = courseName
                        layoutParams = LinearLayout.LayoutParams(270,
                            120,
                            1f)
                        textSize = 18f
                    }

                    val textView2 = TextView(requireContext()).apply {
                        text = marks
                        layoutParams = LinearLayout.LayoutParams(270,
                            120)
                        gravity = Gravity.CENTER
                        setBackgroundColor(Color.parseColor("#A7D5F0"))
                    }

                    rowLayout.addView(textView1)
                    rowLayout.addView(textView2)
                    gradContainer.addView(rowLayout)
                }
            }




    }

}