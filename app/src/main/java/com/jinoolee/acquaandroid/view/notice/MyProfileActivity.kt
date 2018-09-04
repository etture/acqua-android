package com.jinoolee.acquaandroid.view.notice

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jinoolee.acquaandroid.R
import com.jinoolee.acquaandroid.contract.FriendProfileViewContract
import com.jinoolee.acquaandroid.databinding.ActivityMyProfileBinding
import com.jinoolee.acquaandroid.viewmodel.FriendProfileViewModel

class MyProfileActivity : AppCompatActivity(), FriendProfileViewContract {

    companion object {
        private val TAG = MyProfileActivity::class.simpleName
    }

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMyProfileBinding>(this@MyProfileActivity, R.layout.activity_my_profile)
    }

    private val viewModel by lazy {
        FriendProfileViewModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.friend = viewModel
        viewModel.showMyProfile()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearDisposables()
    }

    //====FriendProfileViewContract Implementation

    override fun clearDisposables() {
        Log.i(TAG, "clearDisposables called")
    }
}
