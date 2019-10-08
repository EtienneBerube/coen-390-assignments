package com.example.assignment2.helpers

import android.widget.TextView

class StringProfileInputValidator(
    textView: TextView,
    maxChar: Int?,
    minChar: Int?,
    containsNumbers: Boolean = true,
    val acceptNonAsciiCharacters: Boolean = true) : ProfileInputValidator(textView, maxChar, minChar, containsNumbers) {
    override fun validate(text: String): Boolean {
        try {
            if (maxChar != null && text.length > maxChar) {
                textView.error = "Number cannot be more than $maxChar digits"
                return false
            }

            if (minChar != null && text.length < minChar) {
                textView.error = "Number cannot be less than $minChar digits"
                return false
            }

            val hasDigits = text.toCharArray().any { it.isDigit() }

            if (!containsNumbers && hasDigits) {
                textView.error = "Text cannot contain digits"
                return false
            }

            val hasNonAscii = text.toCharArray().any { (it.toInt()) >= 128 }
            if(!acceptNonAsciiCharacters && hasNonAscii){
                textView.error = "Text can only contain Ascii characters [a-z, A-Z, 0-9 or other special characters]"
                return false
            }

        } catch (ex: NumberFormatException) {
            textView.error = "Number cannot be letters (DUH)"
            return false
        }
        return true
    }
}
