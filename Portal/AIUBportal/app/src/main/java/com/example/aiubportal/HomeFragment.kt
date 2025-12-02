package com.example.aiubportal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var sundayroution : TextView
    lateinit var  mondayroutin: TextView
    lateinit var  turesdayroutin: TextView
    lateinit var  wednesdaydayroutin: TextView
    lateinit var  thrusdayyroutin: TextView
    lateinit var day : TextView


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


        sundayroution.text = "Computer Architecture \n(Section A1, 1:00 PM - 2:30 PM)"
        mondayroutin.text = "Computer Architecture \n(Section A1, 1:00 PM - 2:30 PM)"
        turesdayroutin.text = "Computer Architecture \n(Section A1, 1:00 PM - 2:30 PM)"
        wednesdaydayroutin.text = "Computer Architecture \n(Section A1, 1:00 PM - 2:30 PM)"
        thrusdayyroutin.text = "No Classes";
        day.text = getCurrentWeekday().toString()

    }

}