package com.jinoolee.acquaandroid.contract

import com.jinoolee.acquaandroid.model.AcquaService

interface FriendsListViewContract {
    fun showFriendsList(friendsList: AcquaService.FriendsList)
}