package com.example.course3.dialogs


import com.example.assignment3.R
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


class CourseCreationDialog : DialogFragment() {

    lateinit var nameEditText: EditText
    lateinit var codeEditText: EditText
    lateinit var saveButton: Button
    lateinit var cancelButton: Button
    lateinit var parentActivity: CourseCreationListener


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.dialog_course_creation, container, false)
        nameEditText = view.findViewById(R.id.course_creation_name)
        codeEditText = view.findViewById(R.id.course_creation_code)
        saveButton = view.findViewById(R.id.course_creation_save)
        cancelButton = view.findViewById(R.id.course_creation_cancel)

        cancelButton.setOnClickListener {
            dismiss()
        }

        saveButton.setOnClickListener {
            if(isValideForm()) {
                parentActivity.onCourseCreated(
                    nameEditText.text.toString(),
                    codeEditText.text.toString()
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
            parentActivity = context as CourseCreationListener
        } catch (e: ClassCastException){
            Log.d("course Creation", "Caller activity does not implement courseCreationListener")
            dismiss()
        }
    }

    fun isValideForm(): Boolean{
        return nameEditText.text.isNotBlank() && codeEditText.text.isNotBlank()
    }

    interface CourseCreationListener {
        fun onCourseCreated(name: String, code: String)
    }
}