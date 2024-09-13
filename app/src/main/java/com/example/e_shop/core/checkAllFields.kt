package com.example.e_shop.core

import android.content.Context
import android.util.Patterns
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

fun Context.checkAllFields(
    view: TextInputLayout,
    email: EditText,
    password: EditText,
    passwordLayout: TextInputLayout,
): Boolean {

    // Check if email is empty
    if (email.text.isEmpty()) {
        view.error = "This is required field"
        return false
    }

    // Check email format
    if (!Patterns.EMAIL_ADDRESS.matcher(email.text).matches()) {
        view.error = "Check email format"
        return false
    }

    // Check if password is empty
    if (password.text.isEmpty()) {
        passwordLayout.error = "This is required field"
        return false
    }

    // Check password length
    if (password.text.length < 6) {
        passwordLayout.error = "Password should be at least 6 characters long"
        return false
    }

    return true
}