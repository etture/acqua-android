package com.jinoolee.acquaandroid.model.dataModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WorkEntry(
        @SerializedName("id") @Expose val id: Int,
        @SerializedName("user_id") @Expose val userId: Int,
        @SerializedName("status") @Expose val status: String,
        @SerializedName("company") @Expose val company: String,
        @SerializedName("position") @Expose val position: String,
        @SerializedName("start_date") @Expose val startDate: String,
        @SerializedName("end_date") @Expose val endDate: String
)