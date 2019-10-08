package com.example.assignment3.Components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3.R
import com.example.assignment3.helpers.CourseDao
import com.example.assignment3.models.Course

class CourseAdapter(var courses: List<Course>, val courseDao: CourseDao): RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.course_list_layout, parent, false) as View
        return CourseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.courseAverage.text = "${courseDao.getAverageForCourse(courses[position])}%"
        holder.courseName.text = courses[position].name
    }

    fun updateCourseList(newList: List<Course>) {
        courses = newList
        notifyDataSetChanged()
    }


    class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val courseName: TextView = view.findViewById(R.id.course_name)
        val courseAverage: TextView = view.findViewById(R.id.course_average)
    }
}