package com.example.assignment2.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment2.R
import com.example.assignment2.helpers.NumberProfileInputValidator
import com.example.assignment2.helpers.SharedPreferenceHelper
import com.example.assignment2.helpers.StringProfileInputValidator
import com.example.assignment2.model.Profile
import com.example.assignment2.model.ProfileActivityState

class ProfileActivity : AppCompatActivity() {

    private var state: ProfileActivityState = ProfileActivityState.VIEW
    private lateinit var sharedPreferenceHelper: SharedPreferenceHelper

    private lateinit var nameText: EditText
    private lateinit var studentIdText: EditText
    private lateinit var ageText: EditText
    private lateinit var saveButton: Button
    private var profile: Profile? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setupUi()

        saveButton.setOnClickListener {saveInfo()}

    }

    override fun onStart() {
        super.onStart()

        profile = sharedPreferenceHelper.profile

        if (profile == null) {
            makeEditable(true)
        }
        else {
            makeEditable(false)
            fillProfile(profile!!)
        }
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
            android.R.id.home ->{
                if(profile != null){
                    val intent = Intent(this@ProfileActivity, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    val toast = Toast.makeText(applicationContext, "Please fill in the form before going back in the unknown!", Toast.LENGTH_SHORT)
                    toast.show()
                }
            }
        }

        return true
    }

    override fun onBackPressed() {
        val toast = Toast.makeText(applicationContext,"Don't you dare try to crash my app!", Toast.LENGTH_SHORT)
        toast.show()
    }

    fun setupUi(){
        sharedPreferenceHelper = SharedPreferenceHelper(this@ProfileActivity)

        nameText = findViewById(R.id.name_textview)
        nameText.addTextChangedListener(StringProfileInputValidator(nameText, null,null, false))

        ageText = findViewById(R.id.age_textview)
        ageText.addTextChangedListener(NumberProfileInputValidator(ageText, null, null, 18,99))

        studentIdText = findViewById(R.id.student_id_textview)
        studentIdText.addTextChangedListener(NumberProfileInputValidator(studentIdText, 6, 6))

        saveButton = findViewById(R.id.save_button)

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun validate(): Boolean{
        if(nameText.text.isNullOrEmpty() || nameText.text.isBlank()) return false
        if(ageText.text.isNullOrEmpty()) return false
        if(studentIdText.text.isNullOrEmpty()) return false

        if(nameText.error != null || ageText.error != null || studentIdText.error != null) return false

        return true
    }

    fun saveInfo(){
        if(!validate()){
            val toast = Toast.makeText(applicationContext, "Cannot save current profile. It is incomplete", Toast.LENGTH_SHORT)
            toast.show()
        }else{
            profile = Profile(nameText.text.toString(),ageText.text.toString().toInt(), studentIdText.text.toString().toInt())
            this.sharedPreferenceHelper.saveProfile(profile!!)

            val toast = Toast.makeText(applicationContext, "Profile saved", Toast.LENGTH_SHORT)
            toast.show()

            makeEditable(false)

        }
    }

    fun makeEditable(value: Boolean){
        nameText.isFocusable = value
        nameText.isFocusableInTouchMode = value

        ageText.isFocusable = value
        ageText.isFocusableInTouchMode = value

        studentIdText.isFocusable = value
        studentIdText.isFocusableInTouchMode = value

        saveButton.visibility = if(value) View.VISIBLE else View.INVISIBLE
    }

    fun fillProfile(profile: Profile){
        nameText.setText(profile.name)
        ageText.setText(profile.age.toString())
        studentIdText.setText(profile.studentId.toString())
    }
}
