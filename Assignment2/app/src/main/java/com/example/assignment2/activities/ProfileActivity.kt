package com.example.assignment2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.assignment2.R
import com.example.assignment2.model.ProfileActivityState

class ProfileActivity : AppCompatActivity() {

    private var state: ProfileActivityState = ProfileActivityState.VIEW
    private lateinit var nameText: EditText
    private lateinit var studentIdText: EditText
    private lateinit var ageText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        nameText = findViewById<EditText>(R.id.name_textview)
        ageText = findViewById<EditText>(R.id.age_textview)
        studentIdText = findViewById<EditText>(R.id.student_id_textview)

        val actionBar = actionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.profile_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        when(item.itemId) {
            R.id.edit_profile -> {
                if (state == ProfileActivityState.VIEW){
                    state = ProfileActivityState.EDIT
                    makeEditable(true)
                }else{
                    state = ProfileActivityState.VIEW
                    makeEditable(false)
                }
            }
        }

        return true
    }

    fun validate(): Boolean{
        if(nameText.text.isNullOrEmpty() || nameText.text.isBlank()) return false
        if(ageText.text.isNullOrEmpty()) return false
        if(studentIdText.text.isNullOrEmpty()) return false

        return true
    }

    fun saveInfo(){
        if(!validate()){
            val toast = Toast.makeText(applicationContext, "Cannot save current profile. It is incomplete", Toast.LENGTH_SHORT)
            toast.show()
        }else{

        }
    }

    fun makeEditable(value: Boolean){
        nameText.isFocusable = value
        nameText.isFocusableInTouchMode = value

        ageText.isFocusable = value
        ageText.isFocusableInTouchMode = value

        studentIdText.isFocusable = value
        studentIdText.isFocusableInTouchMode = value
    }
}
