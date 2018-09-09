package com.jinoolee.acquaandroid.model.dataModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FriendsList (
        @SerializedName("friends_list") @Expose val list: List<ProfileBasic>
)