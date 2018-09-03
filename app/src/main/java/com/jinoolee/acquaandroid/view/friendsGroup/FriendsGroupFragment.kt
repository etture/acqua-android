package com.jinoolee.acquaandroid.view.friendsGroup

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jinoolee.acquaandroid.databinding.FragmentFriendsGroupBinding

class FriendsGroupFragment : Fragment() {
    companion object {
        private val TAG = FriendsGroupFragment::class.simpleName

        @JvmStatic
        fun newInstance(): FriendsGroupFragment {
            return FriendsGroupFragment()
        }
    }

    private lateinit var binding: FragmentFriendsGroupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentFriendsGroupBinding.inflate(inflater, container, false)
        val myView = binding.root
        return myView
    }
}