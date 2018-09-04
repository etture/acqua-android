package com.jinoolee.acquaandroid.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.jinoolee.acquaandroid.contract.FriendProfileViewContract
import com.jinoolee.acquaandroid.model.ProfileComplete
import com.jinoolee.acquaandroid.network.ApiClient
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class FriendProfileViewModel(val context: Context): BaseObservable() {
    companion object {
        private val TAG = FriendProfileViewModel::class.simpleName
    }

    @get:Bindable
    var profile: ProfileComplete? = null

    private val disposables = CompositeDisposable()

    private val acquaService = ApiClient(context).create()

    private fun getFriendProfile() {
        val userId = (context as AppCompatActivity).intent.getIntExtra("id", 1)
        disposables.add(acquaService.getCompleteProfile(userId.toString())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {
                            profile = it
                            Log.i(TAG, "onNext received - $profile")
                            notifyChange()
                        },
                        onError = {
                            val nul = null
                            Log.i(TAG, "onError received - $nul")
                        }
                )
        )
    }

    private fun getMyProfile() {
        disposables.add(acquaService.getMyCompleteProfile()
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {
                            profile = it
                            Log.i(TAG, "onNext received - $profile")
                            notifyChange()
                        },
                        onError = {
                            val nul = null
                            Log.i(TAG, "onError received - $nul")
                        }
                )
        )
    }

    //====Functions accessible by view====

    fun showFriendProfile() {
        getFriendProfile()
        Log.i(TAG, "showFriendProfile called")
    }

    fun showMyProfile() {
        getMyProfile()
        Log.i(TAG, "showMyProfile called")
    }

    fun clearDisposables() {
        disposables.clear()
        (context as FriendProfileViewContract).clearDisposables()
        Log.i(TAG, "clearDisposables called")
    }
}