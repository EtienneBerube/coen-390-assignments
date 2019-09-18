package com.example.assignment2.helpers

import android.content.Context
import android.content.SharedPreferences
import com.example.assignment2.R
import com.example.assignment2.model.Profile

class SharedPreferenceHelper(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "ProfilePreference",
        Context.MODE_PRIVATE
    )
    private val context: Context = context
    private var test = "hihihi"

    var profile: Profile ?= null
        get(){

            if(field != null)
                return field

            context.resources.getString(R.string.profile_student_id_shared_resource)

            val name = sharedPreferences.getString(context.resources.getString(R.string.profile_name_shared_resource), null)
            val age = sharedPreferences.getInt(context.resources.getString(R.string.profile_age_shared_resource), -1)
            val studentId = sharedPreferences.getInt(context.resources.getString(R.string.profile_student_id_shared_resource), -1)

            return if(name == null && age == -1 && studentId == -1)
                null
            else if(name == null || age == -1 || studentId == -1)
                throw InstantiationException("Cannot build Profile Object")
            else
                Profile(name,age, studentId )
        }

    fun saveProfile(name: String, age: Int, studentId: Int) {
        val editor = sharedPreferences.edit()
        editor.putString(context.resources.getString(R.string.profile_name_shared_resource), name)
        editor.putInt(context.resources.getString(R.string.profile_age_shared_resource), age)
        editor.putInt(context.resources.getString(R.string.profile_student_id_shared_resource), studentId)
        editor.apply()

        profile = Profile(name,age, studentId)
    }

    fun saveProfile(profile: Profile){
        this.saveProfile(profile.name, profile.age, profile.studentId)
    }
}