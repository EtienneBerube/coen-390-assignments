package com.example.assignment3.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3.Components.AssignmentAdapter
import com.example.assignment3.Components.CourseAdapter
import com.example.assignment3.R
import com.example.assignment3.dialogs.AssignmentCreationDialog
import com.example.assignment3.helpers.CourseDatabase
import com.example.assignment3.models.Assignment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AssignmentActivity : AppCompatActivity(), AssignmentCreationDialog.AssignmentCreationListener  {


    lateinit var recyclerView: RecyclerView
    lateinit var db: CourseDatabase
    lateinit var assignments: MutableList<Assignment>
    lateinit var addAssignmentButton: FloatingActionButton
    var courseId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment)


        courseId = intent.extras!!.getInt("course_id")
    }

    override fun onStart(){
        super.onStart()

        db = CourseDatabase.create(applicationContext)


        val course = db.courseDao().findById(courseId)

        title = "Assignments for ${course.code}"

        assignments = db.assignmentDao().getAllAssignmentsForCourse(courseId)

        setupUi()
    }

    override fun onAssignmentCreated(name: String, grade: Double) {
        val assignment = Assignment(0, name, grade, courseId)
        db.assignmentDao().insertAll(assignment)

        updateCourseList()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    fun setupUi(){
        recyclerView = findViewById(R.id.assignment_list)
        recyclerView.adapter = AssignmentAdapter(assignments, db.assignmentDao())
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        addAssignmentButton = findViewById(R.id.create_assignment_fab)
        addAssignmentButton.setOnClickListener {
            val assignmentCreationDialog = AssignmentCreationDialog()
            assignmentCreationDialog.show(supportFragmentManager,"Course Creator")

        }

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }


    fun updateCourseList(){
        val newList = db.assignmentDao().getAllAssignmentsForCourse(courseId)

        (recyclerView.adapter as AssignmentAdapter).updateAssignmentList(newList)
    }
}
