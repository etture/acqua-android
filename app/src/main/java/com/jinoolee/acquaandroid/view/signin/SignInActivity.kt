package com.jinoolee.acquaandroid.view.signin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jinoolee.acquaandroid.R
import com.jinoolee.acquaandroid.databinding.ActivitySignInBinding
import com.jinoolee.acquaandroid.util.vmb

class SignInActivity : AppCompatActivity() {

    private val vmb by vmb<SignInViewModel, ActivitySignInBinding>(R.layout.activity_sign_in)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        vmb.binding.setLifecycleOwner(this@SignInActivity)


    }
}
