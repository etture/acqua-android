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
import com.jinoolee.acquaandroid.view.calendar.CalendarFragment
import com.jinoolee.acquaandroid.view.feed.FeedFragment
import com.jinoolee.acquaandroid.view.friendsGroup.FriendsGroupFragment
import com.jinoolee.acquaandroid.view.friendsList.FriendsListFragment
import com.jinoolee.acquaandroid.view.notice.NoticeFragment
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

    private val friendsGroupFrag by lazy {
        FriendsGroupFragment.newInstance()
    }

    private val calendarFrag by lazy {
        CalendarFragment.newInstance()
    }

    private val feedFrag by lazy {
        FeedFragment.newInstance()
    }

    private val noticeFrag by lazy {
        NoticeFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.bottomNavigationView.disableShiftMode()
        initFragment()

        RxBottomNavigationView.itemSelections(binding.bottomNavigationView)
                .subscribeBy(
                        onNext = {
                            val transaction = supportFragmentManager.beginTransaction()
                            when(it.itemId){
                                R.id.action_friend -> transaction.replace(R.id.fragment_container, friendsListFrag).commit()
                                R.id.action_group -> transaction.replace(R.id.fragment_container, friendsGroupFrag).commit()
                                R.id.action_calendar -> transaction.replace(R.id.fragment_container, calendarFrag).commit()
                                R.id.action_feed -> transaction.replace(R.id.fragment_container, feedFrag).commit()
                                R.id.action_notice -> transaction.replace(R.id.fragment_container, noticeFrag).commit()
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
        val koreanTestToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjMzLCJpYXQiOjE1MzYwMDUzNTg2OTV9.HRvEoOP8Msb0LlBK7NnCNXGx1CZeaCFNBLeXTTVzFtM"
        val myToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEwLCJpYXQiOjE1MzUxMDkxNDIwMzN9.n-sil6qbc0RE_rBAxWaQHYfb4vumSTmXFWsW-3hDNf4"
        editor.putString("token", koreanTestToken)
        editor.apply()
    }
}