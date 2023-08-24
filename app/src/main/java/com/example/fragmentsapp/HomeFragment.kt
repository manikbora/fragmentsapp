package com.example.fragmentsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class HomeFragment : Fragment() {
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        communicator = activity as Communicator

        val name = communicator.fetchData()
        view.findViewById<TextView>(R.id.txtWelcome)
            .text = getString(R.string.welcome_user_msg, name.uppercase())

        view.findViewById<Button>(R.id.btnLogout).setOnClickListener {
            val loginFragment = LoginFragment()
            communicator.setFragment(loginFragment)
            Toast.makeText(context, getString(R.string.logged_out_msg), Toast.LENGTH_LONG).show()
        }

        return view

    }

}