package com.jinoolee.acquaandroid.view.friendsList

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout

import com.jinoolee.acquaandroid.contract.FriendsListViewContract
import com.jinoolee.acquaandroid.databinding.FragmentFriendsListBinding
import com.jinoolee.acquaandroid.model.AcquaService
import com.jinoolee.acquaandroid.model.FriendsList
import com.jinoolee.acquaandroid.viewmodel.FriendsListViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class FriendsListFragment : Fragment(), FriendsListViewContract {

    companion object {
        private val TAG = FriendsListFragment::class.simpleName

        @JvmStatic
        fun newInstance(): FriendsListFragment {
            return FriendsListFragment()
        }
    }

    private val disposables = CompositeDisposable()

    private lateinit var binding: FragmentFriendsListBinding

    private val viewModel by lazy {
        FriendsListViewModel(activity as Context, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate called")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i(TAG, "onCreateView called")
        // Inflate the layout for this fragment
        binding = FragmentFriendsListBinding.inflate(inflater, container, false)
        binding.friendsListViewModel = viewModel
        val myView = binding.root

        val adapter = FriendsListAdapter()
        binding.friendsListRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.friendsListRecyclerView.adapter = adapter

        viewModel.showFriendsList()

        disposables.add(RxSwipeRefreshLayout.refreshes(binding.friendsListSwipe)
                .subscribeBy(
                        onNext = {
                            viewModel.refreshFriendsList()
                        }
                ))

        return myView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG, "onDestroyView called")
        viewModel.clearDisposables()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy called")
    }

    // =====FriendsListViewContract Implementation=====
    // Change the view based on the FriendsList received

    override fun showFriendsList(friendsList: FriendsList) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearDisposables() {
        disposables.clear()
        Log.i(TAG, "clearDisposables called")
    }
}
