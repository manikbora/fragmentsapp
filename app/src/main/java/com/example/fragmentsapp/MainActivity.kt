package com.example.fragmentsapp

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.fragmentsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Communicator {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragManager: FragmentManager
    private lateinit var sharedPref: SharedPreferences
    private lateinit var editor: Editor
    private var userName = ""
    private var userPassword = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = applicationContext.getSharedPreferences("com.example.fragmentsapp.shared_pref", MODE_PRIVATE)
        editor = sharedPref.edit()

        setFragment(LoginFragment())

        binding.txtLogin.setOnClickListener {
            setFragment(LoginFragment())
        }

        binding.txtRegister.setOnClickListener {
            setFragment(RegisterFragment())
        }
    }

    override fun setFragment(frag: Fragment) {
        fragManager = supportFragmentManager
        fragManager.beginTransaction()
            .replace(R.id.frgContainer, frag)
            .addToBackStack(null)
            .commit()
    }

    override fun saveData(nm: String, pass: String) {
        editor.putString("NAME_KEY", nm)
        editor.putString("PASSWORD_KEY", pass)
        editor.apply()
    }

    override fun fetchData(nm: String, pass: String): Boolean {
        userName = sharedPref.getString("NAME_KEY", "No Name").toString()
        userPassword = sharedPref.getString("PASSWORD_KEY", "No Password").toString()
        if(nm == userName && pass == userPassword) {
            return true
        }
        return false
    }

    override fun fetchData(): String {
        userName = sharedPref.getString("NAME_KEY", "No Name").toString()
        return userName
    }
}