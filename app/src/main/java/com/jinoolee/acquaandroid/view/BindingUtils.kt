package com.jinoolee.acquaandroid.view

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView

@BindingAdapter("data")
fun <T> setRecyclerviewProperties(recyclerview: RecyclerView, data: T) {
    if(recyclerview.adapter is BindableAdapter<*>){
        (recyclerview.adapter as BindableAdapter<T>).setData(data)
    }
}