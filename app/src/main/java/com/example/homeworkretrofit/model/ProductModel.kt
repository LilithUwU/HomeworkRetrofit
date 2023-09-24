package com.example.homeworkretrofit.model

import com.google.gson.annotations.SerializedName

data class ProductModel (
    //we are using serializable so during build it doesn't change the name


    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description:String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("price")
    val price: Int


//    all this is written in as constructor parameter in data class,
//    if we don't use data class the idea will ask us to make getters,
//    but as we know data class is here to make things simpler



)
