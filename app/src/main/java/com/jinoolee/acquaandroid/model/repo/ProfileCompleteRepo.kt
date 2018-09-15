package com.jinoolee.acquaandroid.model.repo

import android.content.Context
import com.jinoolee.acquaandroid.model.AcquaService
import com.jinoolee.acquaandroid.model.network.ApiClient
import com.jinoolee.acquaandroid.model.room.database.TokenDatabase
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ProfileCompleteRepo(context: Context) {
    private val acquaService = ApiClient.getInstance(context)

    fun getCompleteProfile(userId: String) = acquaService!!.getCompleteProfile(userId)
    fun getMyCompleteProfile() = acquaService!!.getMyCompleteProfile()
}