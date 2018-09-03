package com.jinoolee.acquaandroid.view.feed

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jinoolee.acquaandroid.databinding.FragmentFeedBinding
import com.jinoolee.acquaandroid.databinding.FragmentFriendsGroupBinding

class FeedFragment : Fragment() {
    companion object {
        private val TAG = FeedFragment::class.simpleName

        @JvmStatic
        fun newInstance(): FeedFragment {
            return FeedFragment()
        }
    }

    private lateinit var binding: FragmentFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        val myView = binding.root
        return myView
    }
}