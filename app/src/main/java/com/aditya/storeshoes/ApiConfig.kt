package com.aditya.storeshoes

import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
//            val loggingInterceptor = if(BuildConfig.DEBUG) {
//                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//            } else {
//                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
//            }

            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.100.207/restapi_sepatu/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)

        }


    }
}