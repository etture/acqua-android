package com.jinoolee.acquaandroid.model.network

import com.jinoolee.acquaandroid.model.AcquaService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiAuthClient{
    companion object {
        private var INSTANCE: AcquaService? = null

        fun getInstance() : AcquaService? {
            if(INSTANCE == null){
                val retrofit = Retrofit.Builder()
                        .baseUrl("https://acqua-api.herokuapp.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()

                INSTANCE = retrofit.create(AcquaService::class.java)
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}