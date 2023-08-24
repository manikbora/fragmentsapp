package com.example.fragmentsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fragmentsapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        communicator = activity as Communicator

        val name = communicator.fetchData()
        binding.txtWelcome.text = getString(R.string.welcome_user_msg, name.uppercase())

        binding.btnLogout.setOnClickListener {
            val loginFragment = LoginFragment()
            communicator.setFragment(loginFragment)
            Toast.makeText(context, getString(R.string.logged_out_msg, name), Toast.LENGTH_LONG).show()
        }
        return view
    }

}