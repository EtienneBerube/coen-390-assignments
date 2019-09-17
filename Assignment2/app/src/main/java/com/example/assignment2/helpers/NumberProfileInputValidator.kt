package com.example.assignment2.helpers

import android.widget.TextView

class NumberProfileInputValidator(textView: TextView,
                                  maxChar: Int?,
                                  val minNumber: Int = Int.MIN_VALUE,
                                  val maxNumber: Int = Int.MAX_VALUE): ProfileInputValidator(textView, maxChar, true) {
    override fun validate(text: String): Boolean {
        try {
            if (maxChar != null && text.length > maxChar) {
                textView.error = "Number cannot be more than $maxChar digits"
                return false
            }
            if (text.toInt() < minNumber){
                textView.error = "Number cannot be less than $minNumber"
                return false
            }
            if (text.toInt() > maxNumber){
                textView.error = "Number cannot be more than $maxNumber"
                return false
            }
        }catch (ex: NumberFormatException){
            textView.error = "Number cannot be letters (DUH)"
            return false
        }
        return true
    }
}