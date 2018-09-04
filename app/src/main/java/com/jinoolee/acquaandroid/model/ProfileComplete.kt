package com.jinoolee.acquaandroid.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileComplete(
        @SerializedName("complete_profile") @Expose val completeProfile: ProfileExpanded,
        @SerializedName("work_history") @Expose val workHistory: List<WorkEntry>,
        @SerializedName("current_work") @Expose val currentWork: List<WorkEntry>
)