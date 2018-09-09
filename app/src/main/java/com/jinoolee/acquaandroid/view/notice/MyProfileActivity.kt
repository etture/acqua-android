package com.jinoolee.acquaandroid.view.notice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jinoolee.acquaandroid.R
import com.jinoolee.acquaandroid.databinding.ActivityMyProfileBinding
import com.jinoolee.acquaandroid.util.vmb
import com.jinoolee.acquaandroid.view.friendsList.viewModel.ProfileViewModel

class MyProfileActivity : AppCompatActivity() {

    companion object {
        private val TAG = MyProfileActivity::class.simpleName
    }

    private val vmb by vmb<ProfileViewModel, ActivityMyProfileBinding>(R.layout.activity_my_profile)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vmb.binding.setLifecycleOwner(this@MyProfileActivity)
        vmb.viewModel.showMyProfile()
    }
}
