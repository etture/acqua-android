package com.jinoolee.acquaandroid.model.dataModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SignInBody (
        @SerializedName("email") @Expose val email: String,
        @SerializedName("password") @Expose val password: String
)