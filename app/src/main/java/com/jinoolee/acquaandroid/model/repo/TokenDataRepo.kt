package com.jinoolee.acquaandroid.model.repo

import android.content.Context
import android.util.Log
import com.jinoolee.acquaandroid.model.dataModel.SignInBody
import com.jinoolee.acquaandroid.model.dataModel.SignUpBody
import com.jinoolee.acquaandroid.model.network.ApiAuthClient
import com.jinoolee.acquaandroid.model.room.database.TokenDatabase
import com.jinoolee.acquaandroid.model.room.entity.TokenData
import io.reactivex.Flowable

class TokenDataRepo(context: Context) {
    private val acquaService = ApiAuthClient.getInstance()
    private val tokenDatabase = TokenDatabase.getInstance(context)

    fun signIn(signInBody: SignInBody) = acquaService!!.signIn(signInBody)
    fun signUp(signUpBody: SignUpBody) = acquaService!!.signUp(signUpBody)

    fun saveToken(token: TokenData) {
        tokenDatabase!!.tokenDataDao().insertToken(token)
        Log.i("TokenDataRepo", "signIn called")
    }

    fun deleteToken() {
        tokenDatabase!!.tokenDataDao().deleteToken()
        Log.i("TokenDataRepo", "signOut called")
    }

    fun getToken(): Flowable<String> = tokenDatabase!!.tokenDataDao().getToken()

}