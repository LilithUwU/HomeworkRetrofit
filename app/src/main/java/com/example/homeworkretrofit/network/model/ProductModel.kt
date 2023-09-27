package com.example.homeworkretrofit.network.model

import com.google.gson.annotations.SerializedName

class ProductModel(


    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("price")
    val price: Int?

)