package com.jinoolee.acquaandroid.model.repo

import android.content.Context
import com.jinoolee.acquaandroid.model.network.ApiClient

class ProfileCompleteRepo(context: Context) {
    private val acquaService = ApiClient.getInstance(context)

    fun getCompleteProfile(userId: String) = acquaService!!.getCompleteProfile(userId)
    fun getMyCompleteProfile() = acquaService!!.getMyCompleteProfile()
}