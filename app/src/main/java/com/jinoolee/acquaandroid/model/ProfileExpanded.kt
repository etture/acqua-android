package com.jinoolee.acquaandroid.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileExpanded(
        @SerializedName("id") @Expose val id: Int,
        @SerializedName("last_name") @Expose val lastName: String,
        @SerializedName("first_name") @Expose val firstName: String,
        @SerializedName("email") @Expose val email: String,
        @SerializedName("phone_number") @Expose val phoneNumber: String,
        @SerializedName("gender") @Expose val gender: String,
        @SerializedName("birthday") @Expose val birthday: String,
        @SerializedName("profile_picture") @Expose val profilePicture: String,
        @SerializedName("high_school") @Expose val highSchool: String,
        @SerializedName("university_name") @Expose val universityName: String,
        @SerializedName("university_major") @Expose val universityMajor: String,
        @SerializedName("graduate_masters_name") @Expose val graduateMastersName: String,
        @SerializedName("graduate_masters_major") @Expose val graduateMastersMajor: String,
        @SerializedName("graduate_phd_name") @Expose val graduatePhdName: String,
        @SerializedName("graduate_phd_major") @Expose val graduatePhdMajor: String
)