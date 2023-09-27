package com.example.homeworkretrofit.localdata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.homeworkretrofit.localdata.model.ProductModelEntity

@Database(entities = [ProductModelEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): ProductDAO

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): AppDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, AppDatabase::class.java,
                    "product_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

            return instance!!
        }
    }
}