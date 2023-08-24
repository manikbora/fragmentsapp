package com.example.fragmentsapp

import androidx.fragment.app.Fragment

interface Communicator {
    fun saveData(userName: String, password: String)
    fun fetchData(): String
    fun setFragment(frag: Fragment)
}