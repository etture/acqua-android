package com.jinoolee.acquaandroid.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface AcquaService {
    @GET("profiles/basic/{user_id}")
    fun getBasicProfile(@Path("user_id") user_id: String): Flowable<ProfileBasic>

    @GET("friends/get")
    fun getFriendsList(): Flowable<FriendsList>

    data class ProfileBasic (
            @SerializedName("id") @Expose val id: Int,
            @SerializedName("last_name") @Expose val lastName: String,
            @SerializedName("first_name") @Expose val firstName: String,
            @SerializedName("email") @Expose val email: String,
            @SerializedName("phone_number") @Expose val phoneNumber: String
    )

    data class FriendsList (
            @SerializedName("friends_list") @Expose val list: List<ProfileBasic>
    )
}