package com.example.aiubportal

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

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


    private lateinit var course1 : TextView
    private lateinit var course2 : TextView

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

        course1 = view.findViewById<TextView>(R.id.CourseName1)
        course2 = view.findViewById<TextView>(R.id.noticesubject2)

        course1.setOnClickListener {
            val intent = Intent(requireContext(), Gradebook::class.java)
            startActivity(intent)
        }

        course2.setOnClickListener {
            val intent = Intent(requireContext(), Gradebook::class.java)
            startActivity(intent)
        }

    }

}