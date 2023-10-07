package com.tapp.di

import com.tapp.retrofit.MainApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getApi(): MainApi {
        return createRetrofit().create(MainApi::class.java)
    }

    private fun createClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}