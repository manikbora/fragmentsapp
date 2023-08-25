package com.example.fragmentsapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.example.fragmentsapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var communicator: Communicator

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var loginStatus: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        communicator = activity as Communicator


        binding.btnLogin.setOnClickListener {
            hideKeyboard()
            val userName = binding.etUserName.text.toString()
            val password = binding.etPassword.text.toString()
            loginStatus = communicator.fetchData(userName, password)
            if(loginStatus) {
                val fragHome = HomeFragment()
                communicator.setFragment(fragHome)
            } else {
                binding.txtStatus.text = getString(R.string.invalid_login_msg)
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun hideKeyboard() {
        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}