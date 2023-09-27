package com.example.homeworkretrofit.fragments.mapper

import com.example.homeworkretrofit.localdata.model.ProductModelEntity
import com.example.homeworkretrofit.network.model.ProductModel


fun ProductModel.toEntity() =
    ProductModelEntity(
        id = id,
        title = title,
        description = description,
        thumbnail = thumbnail,
        price = price
    )

fun ProductModelEntity.toDomain() =
    ProductModel(
        id = id,
        title = title,
        description = description,
        thumbnail = thumbnail,
        price = price
    )