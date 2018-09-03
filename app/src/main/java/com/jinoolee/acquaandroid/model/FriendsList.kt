package com.jinoolee.acquaandroid.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FriendsList (
        @SerializedName("friends_list") @Expose val list: List<ProfileBasic>
)