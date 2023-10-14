package com.tapp.data

import com.tapp.data.request.LoginRequest
import com.tapp.data.response.ProductsResponse
import com.tapp.data.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface MainApi {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): UserResponse

    @Headers("Content-Type: application/json")
    @GET("products")
    suspend fun getAllProducts(@Header("Authorization") token: String): ProductsResponse
}