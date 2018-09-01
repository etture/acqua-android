package com.jinoolee.acquaandroid.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileBasic (
    @SerializedName("id") @Expose val id: Int,
    @SerializedName("last_name") @Expose val lastName: String,
    @SerializedName("first_name") @Expose val firstName: String,
    @SerializedName("email") @Expose val email: String,
    @SerializedName("phone_number") @Expose val phoneNumber: String
)