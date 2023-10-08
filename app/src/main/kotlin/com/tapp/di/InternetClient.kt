package com.tapp.di

import com.tapp.data.MainApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InternetClient {

    fun createMainApi(): MainApi {
        val retrofit = createRetrofit()
        return retrofit.create(MainApi::class.java)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}