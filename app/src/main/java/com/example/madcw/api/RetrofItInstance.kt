package com.example.madcw.api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofItInstance {
       private const val BASE_URL="http://localhost:8080"


    val instance: LoginApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginApi::class.java)
    }


}