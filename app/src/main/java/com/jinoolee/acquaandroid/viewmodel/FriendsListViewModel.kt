package com.jinoolee.acquaandroid.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.util.Log
import com.android.databinding.library.baseAdapters.BR
import com.jinoolee.acquaandroid.contract.FriendsListViewContract
import com.jinoolee.acquaandroid.model.AcquaService
import com.jinoolee.acquaandroid.network.ApiClient
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.progressDialog

class FriendsListViewModel(val context: Context, val view: FriendsListViewContract) : BaseObservable() {
    @get:Bindable
    var friendsList: MutableList<FriendItemViewModel> = mutableListOf()

    private val disposables = CompositeDisposable()

    private val acquaService = ApiClient(context).create()

    fun showFriendsList() {
        val progress = context.progressDialog(message = "Loading...", title = "Data")
        progress.show()
        disposables.add(acquaService.getFriendsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap{list -> Flowable.fromIterable(list.list)}
                .subscribeBy(
                        onNext = {
                            val name = "${it.firstName} ${it.lastName}"
                            friendsList.add(FriendItemViewModel(name, it.phoneNumber))
                            Log.i("eachItem:", "name: $name, number: ${it.phoneNumber}")
                            Log.i("friendsListLength:", Integer.toString(friendsList.size))
                        },
                        onComplete = {
                            notifyPropertyChanged(BR.friendsList)
                        }
                ))
        progress.dismiss()
    }

    fun clearDisposable() = disposables.clear()
}