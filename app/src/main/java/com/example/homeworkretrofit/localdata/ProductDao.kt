package com.example.homeworkretrofit.localdata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homeworkretrofit.localdata.model.ProductModelEntity

//todo why in some places List<ProductModelEntity> in others : ProductModelEntity

@Dao
interface ProductDAO {
    @Query("SELECT * FROM product")
    fun getAll(): List<ProductModelEntity>

    @Query("SELECT * FROM product WHERE id IN(:productIds)")
    fun loadAllByIds(productIds: IntArray): List<ProductModelEntity>

    @Query("SELECT * FROM product WHERE id = :productId")
    fun loadAllById(productId: Int): ProductModelEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<ProductModelEntity>)

    @Delete
    fun delete(post: ProductModelEntity)
}
