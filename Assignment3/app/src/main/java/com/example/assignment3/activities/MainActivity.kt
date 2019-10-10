package com.example.assignment3.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3.Components.CourseAdapter
import com.example.assignment3.R

import com.example.assignment3.helpers.CourseDatabase
import com.example.assignment3.models.Course
import com.example.course3.dialogs.CourseCreationDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), CourseCreationDialog.CourseCreationListener {


    lateinit var recyclerView: RecyclerView
    lateinit var db: CourseDatabase
    lateinit var courses: MutableList<Course>
    lateinit var addCourseButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Courses"
    }

    override fun onStart(){
        super.onStart()

        db = CourseDatabase.create(applicationContext)

        courses = db.courseDao().getAll()

        setupUi()
    }

    override fun onCourseCreated(name: String, code: String) {
        val course = Course(0, name, code)
        db.courseDao().insertAll(course)

        updateCourseList()
    }

    fun setupUi(){
        recyclerView = findViewById(R.id.course_list)
        recyclerView.adapter = CourseAdapter(courses, db.courseDao())
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        addCourseButton = findViewById(R.id.add_course_fab)
        addCourseButton.setOnClickListener {
            val courseCreationDialog = CourseCreationDialog()
            courseCreationDialog.show(supportFragmentManager,"Course Creator")

        }

    }

    fun updateCourseList(){
        val newList = db.courseDao().getAll()

        (recyclerView.adapter as CourseAdapter).updateCourseList(newList)
    }
}
