package com.example.aiubportal

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var examrtn : Button

    lateinit var registration : Button

    lateinit var offeredcourse : Button

    lateinit var mycurriculam : Button

    lateinit var financials : Button

    lateinit var gradereport : Button

    lateinit var dropapplication : Button

    lateinit var applications : Button

    lateinit var mailbox : Button

    lateinit var library : Button
    lateinit var logout : Button

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
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        examrtn = view.findViewById(R.id.Examroutinbtn)
        examrtn.setOnClickListener {
            val intent = Intent(requireContext(), Examroutine::class.java)
            startActivity(intent)
        }

        registration = view.findViewById(R.id.Registrationbtn)
        registration.setOnClickListener {
            val intent = Intent(requireContext(), Registration::class.java)
            startActivity(intent)
        }

        offeredcourse = view.findViewById(R.id.Offeredcoursebtn)
        offeredcourse.setOnClickListener {
            val intent = Intent(requireContext(), Offeredcourse::class.java)
            startActivity(intent)
        }

        mycurriculam = view.findViewById(R.id.Mycurriculambtn)
        mycurriculam.setOnClickListener {
            val intent = Intent(requireContext(), Mycurriculam::class.java)
            startActivity(intent)
        }

        financials = view.findViewById(R.id.Financialsbtn)
        financials.setOnClickListener {
            val intent = Intent(requireContext(), Finance::class.java)
            startActivity(intent)
        }

        gradereport = view.findViewById(R.id.Gradereportbtn)
        gradereport.setOnClickListener {
            val intent = Intent(requireContext(), Gradereport::class.java)
            startActivity(intent)
        }

        dropapplication = view.findViewById(R.id.Dropapplicationbtn)
        dropapplication.setOnClickListener {
            val intent = Intent(requireContext(), Dropapplications::class.java)
            startActivity(intent)
        }

        applications = view.findViewById(R.id.Applicationsbtn)
        applications.setOnClickListener {
            val intent = Intent(requireContext(), Applications::class.java)
            startActivity(intent)
        }

        mailbox = view.findViewById(R.id.mailbtn)
        mailbox.setOnClickListener {
//            val intent = Intent(requireContext(), Mailbox::class.java)
//            startActivity(intent)
        }

        library = view.findViewById(R.id.Librarybtn)
        library.setOnClickListener {
            val intent = Intent(requireContext(), Library::class.java)
            startActivity(intent)
        }



        // Logout to the startPage
        logout = view.findViewById(R.id.Logoutbtn)
        logout.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }


    }



}