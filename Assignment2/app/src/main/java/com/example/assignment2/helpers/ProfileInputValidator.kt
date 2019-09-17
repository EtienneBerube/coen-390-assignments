package com.example.assignment2.helpers

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView

abstract class ProfileInputValidator(val textView: TextView, val maxChar: Int?, val containsNumbers: Boolean = true): TextWatcher {

    var isValid = true

    override fun afterTextChanged(s: Editable?) {
        if (!validate(textView.text as String)){
            isValid = false
            textView.setError("")
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        //nothing
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        //nothing
    }

    protected abstract fun validate(text: String): Boolean
}