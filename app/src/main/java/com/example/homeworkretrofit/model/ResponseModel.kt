package com.example.homeworkretrofit.model

import com.google.gson.annotations.SerializedName

data class ResponseModel (
    @SerializedName("products")
    val products: List<ProductModel>
)