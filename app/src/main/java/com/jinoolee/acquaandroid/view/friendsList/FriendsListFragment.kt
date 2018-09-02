package com.jinoolee.acquaandroid.view.friendsList

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jinoolee.acquaandroid.contract.FriendsListViewContract
import com.jinoolee.acquaandroid.databinding.FragmentFriendsListBinding
import com.jinoolee.acquaandroid.model.AcquaService
import com.jinoolee.acquaandroid.viewmodel.FriendsListViewModel

class FriendsListFragment : Fragment(), FriendsListViewContract {

    private lateinit var binding: FragmentFriendsListBinding

    private val viewModel by lazy {
        FriendsListViewModel(activity as Context, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentFriendsListBinding.inflate(inflater, container, false)
        binding.friendsListViewModel = viewModel
        val myView = binding.root

        val adapter = FriendsListAdapter()
        binding.friendsListRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.friendsListRecyclerView.adapter = adapter

        viewModel.showFriendsList()

        return myView
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearDisposable()
    }

    companion object {
        @JvmStatic
        fun newInstance(): FriendsListFragment {
            return FriendsListFragment()
        }
    }


    // =====FriendsListViewContract Implementation=====
    // Change the view based on the FriendsList received

    override fun showFriendsList(friendsList: AcquaService.FriendsList) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
