package com.jinoolee.acquaandroid.view.friendsList.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.jinoolee.acquaandroid.model.dataModel.ProfileComplete
import com.jinoolee.acquaandroid.model.repo.ProfileCompleteRepo
import com.jinoolee.acquaandroid.model.repo.TokenDataRepo
import com.jinoolee.acquaandroid.util.plusAssign
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ProfileViewModel(val app: Application) : AndroidViewModel(app) {
    companion object {
        private val TAG = ProfileViewModel::class.simpleName
    }

    //Utils needed in this ViewModel
    private val compositeDisposable = CompositeDisposable()
    private val profileCompleteRepo = ProfileCompleteRepo(getApplication())

    //Data for view
    val profile = MutableLiveData<ProfileComplete>().apply{this.value = null}


    //Call to ProfileCompleteRepo.getCompleteProfile(userId: Int)
    private fun getFriendProfile(userId: Int) {
        compositeDisposable += profileCompleteRepo.getCompleteProfile(userId.toString())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {
                            profile.postValue(it)
                            Log.i(TAG, "onNext received - $profile")
                            Log.i(TAG, "name: ${profile.value?.completeProfile?.firstName}, gender: ${profile.value?.completeProfile?.gender}, birthday: ${profile.value?.completeProfile?.birthday}")
                        },
                        onError = {
                            val nul = null
                            Log.i(TAG, "onError received - $nul")
                        }
                )
    }

    //Call to ProfileCompleteRepo.getMyCompleteProfile()
    private fun getMyProfile() {
        compositeDisposable += profileCompleteRepo.getMyCompleteProfile()
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {
                            profile.postValue(it)
                            Log.i(TAG, "onNext received - $profile")
                        },
                        onError = {
                            val nul = null
                            Log.i(TAG, "onError received - $nul")
                        }
                )
    }

    //Dispose all Rx observers at the end of view lifecycle (ViewModel)
    override fun onCleared() {
        super.onCleared()
        if(!compositeDisposable.isDisposed) compositeDisposable.dispose()
        Log.i(TAG, "onCleared called")
    }

    //========== Functions accessible by view ==========

    //Called by FriendProfileActivity
    fun showFriendProfile(userId: Int) {
        getFriendProfile(userId)
        Log.i(TAG, "showFriendProfile called")
    }

    //Called by MyProfileActivity
    fun showMyProfile() {
        getMyProfile()
        Log.i(TAG, "showMyProfile called")
    }
}