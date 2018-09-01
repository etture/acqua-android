package com.jinoolee.acquaandroid.network

import android.content.Context
import android.text.TextUtils
import com.jinoolee.acquaandroid.model.AcquaService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient(val context: Context) {

    class ApiException(override var message: String) : Exception(message)

    fun create(): AcquaService {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://acqua-api.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        //Attach token to request header
        if (!TextUtils.isEmpty(getToken())) {
            val tokenClient = OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request()
                        val newRequest = request.newBuilder()
                                .addHeader("authorization", getToken())
                                .build()
                        chain.proceed(newRequest)
                    }
                    .build()

            retrofit.client(tokenClient)
        } else throw ApiException("no token provided")

        return retrofit.build().create(AcquaService::class.java)
    }

    private fun getToken(): String {
        val pref = context.getSharedPreferences("authPref", Context.MODE_PRIVATE)
        return pref.getString("token", "")
    }
}