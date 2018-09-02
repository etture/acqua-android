package com.jinoolee.acquaandroid.view

import android.annotation.SuppressLint
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.jakewharton.rxbinding2.support.design.widget.RxBottomNavigationView
import com.jinoolee.acquaandroid.R
import com.jinoolee.acquaandroid.databinding.ActivityHomeBinding
import com.jinoolee.acquaandroid.view.friendsList.FriendsListFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class HomeActivity : AppCompatActivity() {
    companion object {
        private val TAG = HomeActivity::class.simpleName
    }

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityHomeBinding>(this@HomeActivity, R.layout.activity_home)
    }

    private val friendsListFrag by lazy {
        FriendsListFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.bottomNavigationView.disableShiftMode()
        initFragment()

        RxBottomNavigationView.itemSelections(binding.bottomNavigationView)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            val transaction = supportFragmentManager.beginTransaction()
                            when(it.itemId){
                                R.id.action_friend -> transaction.replace(R.id.fragment_container, friendsListFrag).commit()
                            }
                        }
                )

        //temporary store of token in SharedPreferences
        storeToken()
    }

    private fun initFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, friendsListFrag)
        transaction.addToBackStack(null)
        transaction.commit()

        binding.bottomNavigationView.selectedItemId = R.id.action_friend
    }

    @SuppressLint("RestrictedApi")
    fun BottomNavigationView.disableShiftMode() {
        val menuView = getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftingMode = menuView::class.java.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false
            for (i in 0 until menuView.childCount) {
                val item = menuView.getChildAt(i) as BottomNavigationItemView
                item.setShiftingMode(false)
                // set once again checked value, so view will be updated
                item.setChecked(item.itemData.isChecked)
            }
        } catch (e: NoSuchFieldException) {
            Log.e(TAG, "Unable to get shift mode field", e)
        } catch (e: IllegalStateException) {
            Log.e(TAG, "Unable to change value of shift mode", e)
        }
    }

    private fun storeToken() {
        val pref = getSharedPreferences("authPref", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEwLCJpYXQiOjE1MzUxMDkxNDIwMzN9.n-sil6qbc0RE_rBAxWaQHYfb4vumSTmXFWsW-3hDNf4")
        editor.apply()
    }
}