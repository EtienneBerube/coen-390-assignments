package com.example.assignment2.helpers

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceHelper(context: Context) {

    private val sharedPreferences: SharedPreferences

    val profileName: String?
        get() = sharedPreferences.getString("profileName", null)

    init {
        sharedPreferences = context.getSharedPreferences(
            "ProfilePreference",
            Context.MODE_PRIVATE
        )
    }

    fun saveProfileName(name: String) {
        val editor = sharedPreferences.edit()
        editor.putString("profileName", name)
        editor.apply()
    }
}