package com.example.homeworkretrofit.localdata.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductModelEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "price") val price: Int?
)
