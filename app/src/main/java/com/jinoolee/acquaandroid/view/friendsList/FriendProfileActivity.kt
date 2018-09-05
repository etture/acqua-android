package com.jinoolee.acquaandroid.view.friendsList

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.jinoolee.acquaandroid.R
import com.jinoolee.acquaandroid.contract.FriendProfileViewContract
import com.jinoolee.acquaandroid.databinding.ActivityFriendProfileBinding
import com.jinoolee.acquaandroid.viewmodel.ProfileViewModel

class FriendProfileActivity: AppCompatActivity(), FriendProfileViewContract {
    companion object {
        private val TAG = FriendProfileActivity::class.simpleName
    }

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityFriendProfileBinding>(this@FriendProfileActivity, R.layout.activity_friend_profile)
    }

    private val viewModel by lazy {
        ProfileViewModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.friend = viewModel
        viewModel.showFriendProfile()
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