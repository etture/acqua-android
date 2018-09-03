package com.jinoolee.acquaandroid.view.calendar

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jinoolee.acquaandroid.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment() {
    companion object {
        private val TAG = CalendarFragment::class.simpleName

        @JvmStatic
        fun newInstance(): CalendarFragment {
            return CalendarFragment()
        }
    }

    private lateinit var binding: FragmentCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        val myView = binding.root
        return myView
    }
}