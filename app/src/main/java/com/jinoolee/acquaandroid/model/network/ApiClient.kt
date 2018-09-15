package com.jinoolee.acquaandroid.model.network

import android.content.Context
import android.text.TextUtils
import com.jinoolee.acquaandroid.model.AcquaService
import com.jinoolee.acquaandroid.util.TokenManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    class ApiException(override var message: String) : Exception(message)

    companion object {
        private var INSTANCE: AcquaService? = null

        fun getInstance(context: Context): AcquaService? {
            if(INSTANCE == null){
                val retrofit = Retrofit.Builder()
                        .baseUrl("https://acqua-api.herokuapp.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                //Attach token to request header
                if (!TextUtils.isEmpty(getToken(context))) {
                    val tokenClient = OkHttpClient.Builder()
                            .addInterceptor { chain ->
                                val request = chain.request()
                                val newRequest = request.newBuilder()
                                        .addHeader("authorization", getToken(context)!!)
                                        .build()
                                chain.proceed(newRequest)
                            }
                            .build()

                    retrofit.client(tokenClient)
                } else throw ApiException("no token provided")

                INSTANCE = retrofit.build().create(AcquaService::class.java)
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        private fun getToken(context: Context): String? {
            val tokenManager = TokenManager.getInstance(context)
            return tokenManager?.getToken()
        }
    }
}