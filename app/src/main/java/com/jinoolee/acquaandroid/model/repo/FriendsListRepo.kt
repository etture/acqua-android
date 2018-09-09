package com.jinoolee.acquaandroid.model.repo

import android.content.Context
import com.jinoolee.acquaandroid.model.dataModel.FriendsList
import com.jinoolee.acquaandroid.model.network.ApiClient
import io.reactivex.Flowable

class FriendsListRepo(context: Context) {
    private val acquaService = ApiClient(context).create()

    fun getFriendsList(): Flowable<FriendsList> = acquaService.getFriendsList()
}