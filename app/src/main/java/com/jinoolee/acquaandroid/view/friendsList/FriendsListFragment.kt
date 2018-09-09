package com.jinoolee.acquaandroid.view.friendsList

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout
import com.jinoolee.acquaandroid.R

import com.jinoolee.acquaandroid.databinding.FragmentFriendsListBinding
import com.jinoolee.acquaandroid.util.plusAssign
import com.jinoolee.acquaandroid.util.vmb
import com.jinoolee.acquaandroid.view.friendsList.viewModel.FriendsListViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import org.jetbrains.anko.startActivity

class FriendsListFragment : Fragment() {

    companion object {
        private val TAG = FriendsListFragment::class.simpleName

        @JvmStatic
        fun newInstance(): FriendsListFragment {
            return FriendsListFragment()
        }
    }

    private val compositeDisposable = CompositeDisposable()
    private val vmb by vmb<FriendsListViewModel, FragmentFriendsListBinding>(R.layout.fragment_friends_list)
    private val adapter by lazy {
        FriendsListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate called")

        vmb.binding.setLifecycleOwner(this@FriendsListFragment)
        vmb.binding.friendsListRecyclerView.layoutManager = LinearLayoutManager(activity)
        vmb.binding.friendsListRecyclerView.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i(TAG, "onCreateView called")

        compositeDisposable += RxSwipeRefreshLayout.refreshes(vmb.binding.friendsListSwipe)
                .subscribeBy(
                        onNext = {
                            vmb.viewModel.refreshFriendsList()
                        }
                )

        compositeDisposable += adapter.getFriendItemClickSubject()
                .subscribeBy(
                        onNext = {
                            activity!!.startActivity<FriendProfileActivity>("id" to it)
                        }
                )

        vmb.viewModel.showFriendsList()

        return vmb.binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG, "onDestroyView called")
        compositeDisposable.clear()
    }
}

// Inflate the layout for this fragment
//        binding = FragmentFriendsListBinding.inflate(inflater, container, false)
//        binding.viewModel = viewModel

//    private lateinit var binding: FragmentFriendsListBinding

//    private val viewModel by lazy {
//        ViewModelProviders.of(this).get(FriendsListViewModel::class.java)
//        FriendsListViewModel(activity!!.application)
//    }