package com.example.homeworkretrofit.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrfitObject {
    //we write base url here and in a file we are writing the place where we get our data from
    private const val BASE_URL="https://dummyjson.com/"
    val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun createCustomService() : CustomService {
        return  retrofit.create(CustomService::class.java)
    }
}