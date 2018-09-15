package com.jinoolee.acquaandroid.util

import android.content.Context

class TokenManager(context: Context) {
    companion object {
        private var INSTANCE: TokenManager? = null

        fun getInstance(context: Context): TokenManager? {
            if(INSTANCE == null) {
                INSTANCE = TokenManager(context)
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    private val pref = context.getSharedPreferences("tokenPref", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = pref.edit()
        editor.putString("token", token)
        editor.apply()
    }

    fun getToken(): String = pref.getString("token", "")

    fun deleteToken() {
        val editor = pref.edit()
        editor.clear()
        editor.apply()
    }
}