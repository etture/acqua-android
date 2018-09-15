package com.jinoolee.acquaandroid.model.repo

import android.content.Context
import com.jinoolee.acquaandroid.model.AcquaService
import com.jinoolee.acquaandroid.model.dataModel.FriendsList
import com.jinoolee.acquaandroid.model.network.ApiClient
import com.jinoolee.acquaandroid.model.room.database.TokenDatabase
import io.reactivex.Flowable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class FriendsListRepo(context: Context) {
    private val acquaService = ApiClient.getInstance(context)

    fun getFriendsList(): Flowable<FriendsList> = acquaService!!.getFriendsList()
}