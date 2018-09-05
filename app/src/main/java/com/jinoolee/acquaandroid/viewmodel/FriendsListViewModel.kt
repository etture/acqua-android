package com.jinoolee.acquaandroid.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
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

class FriendsListViewModel(val app: Application, val view: FriendsListViewContract) : AndroidViewModel(app) {

    companion object {
        private val TAG = FriendsListViewModel::class.simpleName
    }

    private val disposables = CompositeDisposable()

    private val acquaService = ApiClient(app.applicationContext).create()

    private val _friendsList = MutableLiveData<MutableList<ProfileBasic>>()

    private val _isRefreshing = MutableLiveData<Boolean>()

    val friendsList: LiveData<MutableList<ProfileBasic>>
        get() = _friendsList

    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    private fun getFriendsList() {
        disposables.add(acquaService.getFriendsList()
                .subscribeOn(Schedulers.io())
                .flatMap{list -> Flowable.fromIterable(list.list)}
                .subscribeBy(
                        onNext = {
                            _friendsList.value?.add(it)
                            Log.i("eachItem:", "name: ${it.firstName} ${it.lastName}, number: ${it.phoneNumber}")
                            Log.i("friendsListLength:", Integer.toString(friendsList.value!!.size))
                        },
                        onComplete = {
                            _isRefreshing.postValue(false)
                        }
                ))
    }

    //====Functions accessible by view====

    fun showFriendsList() {
        if(friendsList.value!!.isEmpty()) {
            getFriendsList()
            Log.i(TAG, "showFriendsList called")
        }
    }

    fun refreshFriendsList() {
        friendsList.value?.clear()
        getFriendsList()
        Log.i(TAG, "refreshFriendsList called")
    }

    fun clearDisposables() {
        disposables.clear()
        view.clearDisposables()
        Log.i(TAG, "clearDisposables called")
    }
}