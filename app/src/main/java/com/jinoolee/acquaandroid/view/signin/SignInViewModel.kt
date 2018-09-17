package com.jinoolee.acquaandroid.view.signin

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.jinoolee.acquaandroid.model.dataModel.SignInBody
import com.jinoolee.acquaandroid.model.repo.TokenDataRepo
import com.jinoolee.acquaandroid.util.SignInManager
import com.jinoolee.acquaandroid.util.plusAssign
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SignInViewModel(app: Application) : AndroidViewModel(app) {
    companion object {
        private val TAG = SignInViewModel::class.simpleName
    }

    //Utils needed in this ViewModel
    private val compositeDisposable = CompositeDisposable()
    private val repo = TokenDataRepo(getApplication())
    private val signInManager = SignInManager.getInstance(getApplication())

    //Data for view
    private val signedIn = MutableLiveData<Boolean>().apply { this.value = signInManager?.isSignedIn() }

    private fun signInWithServer(email: String, password: String) {
        val body = SignInBody(email, password)
        Log.i(TAG, "signInWithServer called")

        compositeDisposable += repo.signIn(body)
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = {
//                            val token = TokenData(it.token)
//                            repo.signOut()
//                            repo.signIn(token)
                            signInManager?.signIn(it.token) //saving token and signed-in: true to SharedPreferences
                            Log.i(TAG, "repo.signIn(body) called, onNext invoked")
                            Log.i(TAG, "token saved")
                        },
                        onError = {
                            Log.i(TAG, "signIn failed")
                            Log.i(TAG, it.message)
                        },
                        onComplete = {
                            signedIn.postValue(signInManager?.isSignedIn())
                            Log.i(TAG, "signIn succeeded")
                        }
                )
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
        Log.i(TAG, "onCleared called")
    }

    //========== Functions accessible by view ==========

    fun isSignedIn(): LiveData<Boolean> = signedIn

    fun signIn(email: String, password: String) {
        signInWithServer(email, password)
        Log.i(TAG, "signIn called from View")
    }

}