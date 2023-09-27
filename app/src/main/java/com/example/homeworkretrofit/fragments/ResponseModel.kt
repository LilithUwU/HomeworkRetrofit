package com.example.homeworkretrofit.fragments

import com.example.homeworkretrofit.fragments.model.ProductModelEntity
import com.google.gson.annotations.SerializedName

data class ResponseModel (
    @SerializedName("products")
    val products: List<ProductModelEntity>
)