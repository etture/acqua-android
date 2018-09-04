package com.jinoolee.acquaandroid.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.util.Log
import com.jinoolee.acquaandroid.contract.FriendsListViewContract
import com.jinoolee.acquaandroid.model.ProfileBasic
import com.jinoolee.acquaandroid.network.ApiClient
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class FriendsListViewModel(val context: Context, val view: FriendsListViewContract) : BaseObservable() {

    companion object {
        private val TAG = FriendsListViewModel::class.simpleName
    }

    @get:Bindable
    var friendsList: MutableList<ProfileBasic> = mutableListOf()

    @get:Bindable
    var isRefreshing: Boolean = false

    private val disposables = CompositeDisposable()

    private val acquaService = ApiClient(context).create()

    private fun getFriendsList() {
        disposables.add(acquaService.getFriendsList()
                .subscribeOn(Schedulers.io())
                .flatMap{list -> Flowable.fromIterable(list.list)}
                .subscribeBy(
                        onNext = {
                            friendsList.add(it)
                            Log.i("eachItem:", "name: ${it.firstName} ${it.lastName}, number: ${it.phoneNumber}")
                            Log.i("friendsListLength:", Integer.toString(friendsList.size))
                        },
                        onComplete = {
                            isRefreshing = false
                            notifyChange()
                        }
                ))
    }

    //====Functions accessible by view====

    fun showFriendsList() {
        if(friendsList.isEmpty()) {
            getFriendsList()
            Log.i(TAG, "showFriendsList called")
        }
    }

    fun refreshFriendsList() {
        friendsList.clear()
        getFriendsList()
        Log.i(TAG, "refreshFriendsList called")
    }

    fun clearDisposables() {
        disposables.clear()
        view.clearDisposables()
        Log.i(TAG, "clearDisposables called")
    }
}