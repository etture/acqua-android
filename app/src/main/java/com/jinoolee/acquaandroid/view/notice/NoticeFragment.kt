package com.jinoolee.acquaandroid.view.notice

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.RxView
import com.jinoolee.acquaandroid.databinding.FragmentNoticeBinding
import io.reactivex.rxkotlin.subscribeBy
import org.jetbrains.anko.startActivity

class NoticeFragment : Fragment() {
    companion object {
        private val TAG = NoticeFragment::class.simpleName

        @JvmStatic
        fun newInstance(): NoticeFragment {
            return NoticeFragment()
        }
    }

    private lateinit var binding: FragmentNoticeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoticeBinding.inflate(inflater, container, false)
        val myView = binding.root

        RxView.clicks(binding.myProfile)
                .subscribeBy(
                        onNext = {
                            activity!!.startActivity<MyProfileActivity>()
                        }
                )

        return myView
    }
}