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

    override fun saveData(userName: String, password: String) {
        editor.putString("USER_NAME", userName)
        editor.putString("PASSWORD", password)
        editor.apply()
    }

    override fun fetchData(): String {
        val userName = sharedPref.getString("USER_NAME", "No Name").toString()
        if(userName != "No Name") {
            return userName
        }
        return userName
    }
}