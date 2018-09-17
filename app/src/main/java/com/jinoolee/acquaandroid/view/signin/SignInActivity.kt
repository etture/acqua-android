package com.jinoolee.acquaandroid.view.signin

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jakewharton.rxbinding2.view.RxView
import com.jinoolee.acquaandroid.R
import com.jinoolee.acquaandroid.databinding.ActivitySignInBinding
import com.jinoolee.acquaandroid.util.plusAssign
import com.jinoolee.acquaandroid.util.vmb
import com.jinoolee.acquaandroid.view.HomeActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.startActivity

class SignInActivity : AppCompatActivity() {
    companion object {
        private val TAG = SignInViewModel::class.simpleName
    }

    private val compositeDisposable = CompositeDisposable()
    private val vmb by vmb<SignInViewModel, ActivitySignInBinding>(R.layout.activity_sign_in)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        vmb.binding.setLifecycleOwner(this@SignInActivity)

        compositeDisposable += RxView.clicks(vmb.binding.btnSignIn)
                .subscribeBy(
                        onNext = {
                            val email = vmb.binding.fieldEmail.text.toString()
                            val password = vmb.binding.fieldPassword.text.toString()
                            vmb.viewModel.signIn(email, password)
                        }
                )

        vmb.viewModel.isSignedIn()
                .observe(this@SignInActivity, Observer {
                    if(it!!) {
                        Log.i(TAG, "isSignedIn: true")
                        startActivity(intentFor<HomeActivity>().singleTop())
                    }else{
                        Log.i(TAG, "isSignedIn: false")
                    }
                })
    }

    override fun onStart() {
        super.onStart()
        initialUpdateUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun initialUpdateUI() {
        if(vmb.viewModel.isSignedIn().value!!){
            Log.i(TAG, "initial isSignedIn: true")
            startActivity(intentFor<HomeActivity>().singleTop())
        }else{
            Log.i(TAG, "initial isSignedIn: false")
        }
    }
}
