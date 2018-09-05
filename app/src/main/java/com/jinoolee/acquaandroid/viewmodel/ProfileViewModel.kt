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

class ProfileViewModel(val context: Context): BaseObservable() {
    companion object {
        private val TAG = ProfileViewModel::class.simpleName
    }

    @get:Bindable
    var profile: ProfileComplete? = null

    private val disposables = CompositeDisposable()

    private val acquaService = ApiClient(context).create()

    //Call to AcquaService.getCompleteProfile(userId: Int)
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

    //Call to AcquaService.getMyCompleteProfile()
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

    //Called by FriendProfileActivity
    fun showFriendProfile() {
        getFriendProfile()
        Log.i(TAG, "showFriendProfile called")
    }

    //Called by MyProfileActivity
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