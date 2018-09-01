package com.jinoolee.acquaandroid.network

import com.jinoolee.acquaandroid.model.AcquaService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiAuthClient{
    companion object {
        fun create() : AcquaService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://acqua-api.herokuapp.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit.create(AcquaService::class.java)
        }
    }
}