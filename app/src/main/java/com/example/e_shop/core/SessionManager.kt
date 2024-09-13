package com.example.e_shop.core

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = prefs.edit()

    fun saveUserId(userId: String) {
        editor.putString("USER_ID", userId)
        editor.apply()
    }

    fun getUserId(): String? {
        return prefs.getString("USER_ID", null)
    }

    fun clearSession() {
        editor.clear()
        editor.apply()
    }
}