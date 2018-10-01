package com.jinoolee.acquaandroid.view

import android.annotation.SuppressLint
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.jakewharton.rxbinding2.support.design.widget.RxBottomNavigationView
import com.jinoolee.acquaandroid.R
import com.jinoolee.acquaandroid.databinding.ActivityHomeBinding
import com.jinoolee.acquaandroid.util.vmb
import com.jinoolee.acquaandroid.view.calendar.CalendarFragment
import com.jinoolee.acquaandroid.view.feed.FeedFragment
import com.jinoolee.acquaandroid.view.friendsGroup.FriendsGroupFragment
import com.jinoolee.acquaandroid.view.friendsList.FriendsListFragment
import com.jinoolee.acquaandroid.view.notice.NoticeFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
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

    private val bottomNavigationView by lazy {
        binding.appBarHome?.contentHome?.bottomNavigationView
    }
    private val drawerLayout by lazy {
        binding.drawerLayoutHome
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bottomNavigationView?.disableShiftMode()
        initFragment()

        //Navigation View settings
        setSupportActionBar(binding.appBarHome?.homeToolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, binding.appBarHome?.homeToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        //End Navigation View settings

        RxBottomNavigationView.itemSelections(bottomNavigationView!!)
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
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            //Nothing happens when back pressed at home screen
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.nav, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item?.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun initFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, friendsListFrag)
        transaction.addToBackStack(null)
        transaction.commit()

        bottomNavigationView?.selectedItemId = R.id.action_friend
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
}