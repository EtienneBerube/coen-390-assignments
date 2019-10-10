package com.example.assignment3.Components

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3.R
import com.example.assignment3.activities.AssignmentActivity
import com.example.assignment3.helpers.CourseDao
import com.example.assignment3.models.Course


class CourseAdapter(var courses: MutableList<Course>, val courseDao: CourseDao): RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.course_element, parent, false) as View
        return CourseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {

        if(courses[position].name!!.length < 9){
            holder.courseName.text = courses[position].name
        }else {
            holder.courseName.text = courses[position].name!!.substring(0..8) + "..."
        }

        holder.courseCode.text = courses[position].code
        holder.courseAverage.text = "Average: ${courseDao.getAverageForCourse(courses[position])}%"

        holder.deleteButton.setOnClickListener {
            courseDao.delete(courses[position])
            notifyItemRemoved(position)
            courses.removeAt(position)
        }

        holder.card.setOnClickListener {
            val i = Intent(holder.context, AssignmentActivity::class.java)
            i.putExtra("course_id", courses[position].id)
            holder.context.startActivity(i)
        }
    }

    fun updateCourseList(newList: MutableList<Course>) {
        courses = newList
        notifyDataSetChanged()
    }


    class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val courseName: TextView = view.findViewById(R.id.course_name)
        val courseAverage: TextView = view.findViewById(R.id.course_average)
        val courseCode: TextView = view.findViewById(R.id.course_code)
        val deleteButton: ImageButton = view.findViewById(R.id.delete_course_button)
        val card: CardView = view.findViewById(R.id.card_course)
        val context = view.context
    }
}