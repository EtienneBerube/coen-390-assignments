package com.example.assignment2.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment2.R
import com.example.assignment2.helpers.SharedPreferenceHelper






class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferenceHelper: SharedPreferenceHelper
    private lateinit var profileButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferenceHelper = SharedPreferenceHelper(this@MainActivity)

        profileButton = findViewById<Button>(R.id.profile_button)

        profileButton.setOnClickListener {goToProfileActivity()}
    }

    override fun onStart() {
        super.onStart()
        val name = sharedPreferenceHelper.profileName
        if (name == null)
            goToProfileActivity()
        else
            profileButton.text = name
    }

    private fun goToProfileActivity() {
        val intent = Intent(this@MainActivity, ProfileActivity::class.java)
        startActivity(intent)
    }


}
