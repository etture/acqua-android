package com.jinoolee.acquaandroid.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable

class MainViewModel: BaseObservable() {

    @get:Bindable
    var result: String = ""
        set(value){
            field = value
            notifyChange()
        }
}