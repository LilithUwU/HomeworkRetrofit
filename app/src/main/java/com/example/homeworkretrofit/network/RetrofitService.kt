package com.example.homeworkretrofit.network

import com.example.homeworkretrofit.model.ProductModel
import com.example.homeworkretrofit.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
//todo why are we using a file, why not a class?
interface CustomService{
    //so here we are getting to the place where we will take our data
    @GET("/products")
    suspend fun getProductList(): Response <ResponseModel>
}