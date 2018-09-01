package com.jinoolee.acquaandroid.view

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.jinoolee.acquaandroid.BR
import com.jinoolee.acquaandroid.viewmodel.MainViewModel
import com.jinoolee.acquaandroid.R
import com.jinoolee.acquaandroid.databinding.ActivityMainBinding
import com.jinoolee.acquaandroid.model.AcquaService
import com.jinoolee.acquaandroid.network.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this@MainActivity, R.layout.activity_main)
    }

    private val mainModel by lazy {
        MainViewModel()
    }

    private val acquaService by lazy {
        ApiClient(this).create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.setVariable(BR.mainModel, mainModel)
        binding.executePendingBindings()
    }

    fun onSubmit(view: View) {
        val userId: String = binding.apiRef.text.toString()

        acquaService.getBasicProfile(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            val profile: AcquaService.ProfileBasic? = it
                            mainModel.result = "ID: ${profile!!.id} \nFirst Name: ${profile.firstName} \nLast Name: ${profile.lastName} \nEmail: ${profile.email} \nPhone Number: ${profile.phoneNumber}"
                            Log.i("result", mainModel.result)
                        },
                        onError = {mainModel.result = "not executed properly"},
                        onComplete = {}
                )
    }
}
