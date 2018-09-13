package com.jinoolee.acquaandroid.model.dataModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SignInResponse (
        @SerializedName("isSuccess") @Expose val isSuccess: Boolean,
        @SerializedName("token") @Expose val token: String
)