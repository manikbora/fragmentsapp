package com.example.fragmentsapp

import androidx.fragment.app.Fragment

interface Communicator {
    fun setFragment(frag: Fragment)
    fun saveData(nm: String, pass: String)
    fun fetchData(): String
    fun fetchData(nm: String, pass: String): Boolean
}