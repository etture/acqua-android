package com.jinoolee.acquaandroid.util

import android.content.Context

//Handles sign-in and sign-out status, saving token and signed-in boolean into SharedPreferences
class SignInManager(context: Context) {
    companion object {
        private var INSTANCE: SignInManager? = null

        fun getInstance(context: Context): SignInManager? {
            if(INSTANCE == null) {
                INSTANCE = SignInManager(context)
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    private val pref = context.getSharedPreferences("authPref", Context.MODE_PRIVATE)

    fun signIn(token: String) {
        val editor = pref.edit()
        editor.putString("token", token)
        editor.putBoolean("signedIn", true)
        editor.apply()
    }

    fun getToken(): String = pref.getString("token", "")

    fun isSignedIn(): Boolean = pref.getBoolean("signedIn", false)

    fun signOut() {
        val editor = pref.edit()
        editor.clear()
        editor.apply()
    }
}