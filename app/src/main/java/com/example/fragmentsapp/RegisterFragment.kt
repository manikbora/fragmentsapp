package com.example.fragmentsapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.example.fragmentsapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var communicator: Communicator

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        communicator = activity as Communicator

        binding.btnRegister.setOnClickListener {
            hideKeyboard()
            val userName = binding.etUserName.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if(userName != "" && password != "") {
                communicator.saveData(userName, password)
                binding.txtStatus.text = getString(R.string.registration_success_msg)
            } else {
                binding.txtStatus.text = getString(R.string.please_provide_complete_information)
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