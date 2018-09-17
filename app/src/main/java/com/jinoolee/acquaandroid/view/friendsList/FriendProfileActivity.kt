package com.jinoolee.acquaandroid.view.friendsList

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.jinoolee.acquaandroid.R
import com.jinoolee.acquaandroid.databinding.ActivityFriendProfileBinding
import com.jinoolee.acquaandroid.util.vmb
import com.jinoolee.acquaandroid.view.friendsList.viewModel.ProfileViewModel

class FriendProfileActivity : AppCompatActivity() {
    companion object {
        private val TAG = FriendProfileActivity::class.simpleName
    }

    private val vmb by vmb<ProfileViewModel, ActivityFriendProfileBinding>(R.layout.activity_friend_profile)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vmb.binding.setLifecycleOwner(this@FriendProfileActivity)

        //ActionBar settings
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = getString(R.string.profile)

        val userId = intent.getIntExtra("id", 1)
        vmb.viewModel.showFriendProfile(userId)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}

//    private val binding by lazy {
//        DataBindingUtil.setContentView<ActivityFriendProfileBinding>(this@FriendProfileActivity, R.layout.activity_friend_profile)
//    }

//    private val viewModel by lazy {
////        ViewModelProviders.of(this).get(ProfileViewModel::class.java)
//        ProfileViewModel(this)
//    }