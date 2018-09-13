package com.jinoolee.acquaandroid.model

import com.jinoolee.acquaandroid.model.dataModel.*
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AcquaService {
    @POST("auth/signup")
    fun signUp(@Body signUpBody: SignUpBody): Flowable<SignUpResponse>

    @POST("auth/signin")
    fun signIn(@Body signInBody: SignInBody): Flowable<SignInResponse>

    @GET("profiles/basic/{user_id}")
    fun getBasicProfile(@Path("user_id") userId: String): Flowable<ProfileBasic>

    @GET("friends/get")
    fun getFriendsList(): Flowable<FriendsList>

    @GET("profiles/{user_id}")
    fun getCompleteProfile(@Path("user_id") userId: String): Flowable<ProfileComplete>

    @GET("profiles/self")
    fun getMyCompleteProfile(): Flowable<ProfileComplete>
}