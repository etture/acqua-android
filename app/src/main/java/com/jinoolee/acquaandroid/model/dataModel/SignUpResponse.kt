package com.jinoolee.acquaandroid.model.dataModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SignUpResponse (
        @SerializedName("isSuccess") @Expose val isSuccess: Boolean,
        @SerializedName("user") @Expose val user: ProfileBasic,
        @SerializedName("token") @Expose val token: String
)