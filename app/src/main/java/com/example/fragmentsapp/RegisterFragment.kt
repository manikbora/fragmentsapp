package com.example.fragmentsapp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment : Fragment() {
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        communicator = activity as Communicator

        view.findViewById<Button>(R.id.btnRegister).setOnClickListener {
            hideKeyboard()
            val userName = view.findViewById<TextInputEditText>(R.id.etUserName).text.toString()
            val password = view.findViewById<TextInputEditText>(R.id.etPassword).text.toString()

            communicator.saveData(userName, password)
            view.findViewById<TextView>(R.id.txtStatus)
                .text = getString(R.string.registration_success_msg)
        }

        return view
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}