package com.example.madcw.api

import com.example.madcw.Entity.LoginRequest
import com.example.madcw.Entity.LoginRespones
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("api/auth/login")
    suspend fun loginUser(
        @Body request: LoginRequest
    ): Response<LoginRespones>

}

