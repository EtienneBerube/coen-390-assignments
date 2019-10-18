package com.example.assignment3.Components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3.R
import com.example.assignment3.helpers.AssignmentDao
import com.example.assignment3.models.Assignment


class AssignmentAdapter(var assignments: MutableList<Assignment>, val dao: AssignmentDao):
    RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentViewHolder {
        val view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.assignment_element, parent, false) as View
        return AssignmentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return assignments.size
    }

    override fun onBindViewHolder(holder: AssignmentViewHolder, position: Int) {
        holder.assignmentName.text = assignments[position].name
        holder.assignmentGrade.text = "${assignments[position].grade}"
        holder.deleteButton.setOnClickListener {
            dao.delete(assignments[position])
            notifyItemRemoved(position)
            assignments.removeAt(position)
        }
    }

    fun updateAssignmentList(newList: MutableList<Assignment>) {
        assignments = newList
        notifyDataSetChanged()
    }

    class AssignmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val assignmentName: TextView = view.findViewById(R.id.assignment_name)
        val assignmentGrade: TextView = view.findViewById(R.id.assignment_grade)
        val deleteButton: ImageButton = view.findViewById(R.id.delete_assignment_button)
    }
}