package com.jinoolee.acquaandroid.view.friendsList.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.jinoolee.acquaandroid.model.dataModel.FriendsList
import com.jinoolee.acquaandroid.model.repo.FriendsListRepo
import com.jinoolee.acquaandroid.model.repo.TokenDataRepo
import com.jinoolee.acquaandroid.util.plusAssign
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class FriendsListViewModel(val app: Application) : AndroidViewModel(app) {

    companion object {
        private val TAG = FriendsListViewModel::class.simpleName
    }

    //Data for view
    val friendsList = MutableLiveData<FriendsList>().apply { this.value = FriendsList(emptyList()) }
    val isRefreshing = MutableLiveData<Boolean>().apply { this.value = false }

    //Utils needed in this ViewModel
    private val compositeDisposable = CompositeDisposable()
    private val friendsListRepo = FriendsListRepo(getApplication())

    //Return list of friends of this user from Repository
    private fun getFriendsList() {
        compositeDisposable += friendsListRepo.getFriendsList()
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {
                            friendsList.postValue(it)
                        },
                        onComplete = {
                            isRefreshing.postValue(false)
                        }
                )
    }

    //Dispose all Rx observers at the end of view lifecycle (ViewModel)
    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
        Log.i(TAG, "onCleared called")
    }

    //========== Functions accessible by view ==========

    fun showFriendsList() {
        if (friendsList.value!!.list.isEmpty()) {
            getFriendsList()
            Log.i(TAG, "showFriendsList called")
        }

        Log.i(TAG, "showFriendsList called")
        Log.i(TAG, "friendsList: $friendsList, length: ${friendsList.value!!.list.size}")
    }

    fun refreshFriendsList() {
        getFriendsList()
        Log.i(TAG, "refreshFriendsList called")
    }
}