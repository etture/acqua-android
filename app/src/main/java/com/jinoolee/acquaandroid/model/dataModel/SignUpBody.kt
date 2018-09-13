package com.jinoolee.acquaandroid.model.dataModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SignUpBody (
        @SerializedName("email") @Expose val email: String,
        @SerializedName("password") @Expose val password: String,
        @SerializedName("first_name") @Expose val firstName: String,
        @SerializedName("last_name") @Expose val lastName: String,
        @SerializedName("phone_number") @Expose val phoneNumber: String
)