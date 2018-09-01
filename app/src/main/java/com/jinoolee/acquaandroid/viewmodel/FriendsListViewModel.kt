package com.jinoolee.acquaandroid.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.util.Log
import com.jinoolee.acquaandroid.contract.FriendsListViewContract
import com.jinoolee.acquaandroid.model.AcquaService
import com.jinoolee.acquaandroid.network.ApiClient
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class FriendsListViewModel(val context: Context, val view: FriendsListViewContract) : BaseObservable() {
    @get:Bindable
    var friendsList: String = ""
        set(value) {
            field = value
            notifyChange()
        }

    private val acquaService = ApiClient(context).create()

    fun showFriendsList() {
        acquaService.getFriendsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap{list -> Flowable.fromIterable(list.list)}
                .subscribeBy(
                        onNext = {
                            friendsList += "ID: ${it!!.id} \nFirst Name: ${it.firstName} \nLast Name: ${it.lastName} \nEmail: ${it.email} \nPhone Number: ${it.phoneNumber}\n\n"
                            Log.i("getFriendsList", "friendsList: " + friendsList)
                        },
                        onError = {
                            Log.e("getFriendsList", "some error")
                            throw it
                        }
                )

//                acquaService.getBasicProfile("16")
//                .observeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribeBy(
//                        onNext = {
//                            friendsList = "ID: ${it!!.id} \nFirst Name: ${it.firstName} \nLast Name: ${it.lastName} \nEmail: ${it.email} \nPhone Number: ${it.phoneNumber}\n\n"
//                            Log.i("getFriendsList", "email: " + it.email)
//                        },
//                        onError = {
//                            Log.e("getFriendsList", "some error")
//                            throw it
//                        }
//                )


//        view.showFriendsList()
    }
}