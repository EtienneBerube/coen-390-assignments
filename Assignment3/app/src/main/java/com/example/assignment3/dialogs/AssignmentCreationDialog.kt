package com.example.assignment3.dialogs

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.assignment3.R
import android.graphics.Color
import android.widget.TextView





class AssignmentCreationDialog : DialogFragment() {

    lateinit var nameEditText: EditText
    lateinit var gradeEditText: EditText
    lateinit var saveButton: Button
    lateinit var cancelButton: Button

    lateinit var parentActivity: AssignmentCreationListener


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.dialog_assignment_creation, container, false)
        nameEditText = view.findViewById(R.id.assignment_creation_name)
        gradeEditText = view.findViewById(R.id.assignment_creation_grade)
        saveButton = view.findViewById(R.id.assignment_creation_save)
        cancelButton = view.findViewById(R.id.assignment_creation_cancel)

        cancelButton.setOnClickListener {
            dismiss()
        }

        saveButton.setOnClickListener {
            if(isValidateForm() && gradeEditText.text.toString().toDouble() in 0.0..100.0) {
                parentActivity.onAssignmentCreated(
                    nameEditText.text.toString(),
                    gradeEditText.text.toString().toDouble()
                )
                dismiss()
            }else{
                val toast = Toast.makeText(context,"Invalid form", Toast.LENGTH_SHORT)
                toast.show()
            }
        }

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            parentActivity = context as AssignmentCreationListener
        } catch (e: ClassCastException){
            Log.d("Assignment Creation", "Caller activity does not implement AssignmentCreationListener")
            dismiss()
        }
    }

    fun isValidateForm(): Boolean{
        return nameEditText.text.isNotBlank() && gradeEditText.text.isBlank()
    }

    interface AssignmentCreationListener {
        fun onAssignmentCreated(name: String, grade: Double)
    }
}