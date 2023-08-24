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

class LoginFragment : Fragment() {
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        communicator = activity as Communicator

        val name = communicator.fetchData()
        view.findViewById<Button>(R.id.btnLogin)
            .setOnClickListener {
                hideKeyboard()
                val userName = view.findViewById<TextInputEditText>(R.id.etUserName).text.toString()
                //val password = view.findViewById<TextInputEditText>(R.id.etPassword).text.toString()
                if(name == userName) {
                    val fragHome = HomeFragment()
                    communicator.setFragment(fragHome)
                } else {
                    view.findViewById<TextView>(R.id.txtStatus)
                        .text = getString(R.string.invalid_login_msg)
                }
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