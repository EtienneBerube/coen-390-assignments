package com.example.assignment3.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.assignment3.Components.CourseAdapter
import com.example.assignment3.R
import com.example.assignment3.helpers.CourseDao
import com.example.assignment3.helpers.CourseDatabase
import com.example.assignment3.models.Course
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var db: CourseDatabase
    lateinit var courses: List<Course>
    lateinit var addCourseButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart(){
        super.onStart()

        db = CourseDatabase.create(applicationContext)

        courses = db.courseDao().getAll()

        setupUi()
    }

    fun setupUi(){
        recyclerView = findViewById(R.id.course_list)
        recyclerView.adapter = CourseAdapter(courses, db.courseDao())
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        addCourseButton = findViewById(R.id.add_course_fab)
        addCourseButton.setOnClickListener {
            val course = Course(0, "Coen390:"+(Math.random()*100+1).toInt(), "That thing")
            db.courseDao().insertAll(course)

            updateCourseList()
        }

    }

    fun updateCourseList(){
        val newList = db.courseDao().getAll()

        (recyclerView.adapter as CourseAdapter).updateCourseList(newList)
    }
}
