package com.example.fragmentsapp

import androidx.fragment.app.Fragment

interface Communicator {
    fun saveData(name: String, password: String)
    fun fetchData(name: String, password: String): Boolean
    fun fetchData(): String
    fun setFragment(frag: Fragment)
}