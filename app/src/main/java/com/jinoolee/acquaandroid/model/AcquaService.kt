package com.jinoolee.acquaandroid.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface AcquaService {
    @GET("profiles/basic/{user_id}")
    fun getBasicProfile(@Path("user_id") userId: String): Flowable<ProfileBasic>

    @GET("friends/get")
    fun getFriendsList(): Flowable<FriendsList>

    @GET("profiles/{user_id}")
    fun getCompleteProfile(@Path("user_id") userId: String): Flowable<ProfileComplete>

    @GET("profiles/self")
    fun getMyCompleteProfile(): Flowable<ProfileComplete>
}