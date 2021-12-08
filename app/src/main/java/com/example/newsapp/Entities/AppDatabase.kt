package com.example.newsapp.Entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.Article

@Database(entities = arrayOf(Article::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    companion object{
        private val DB_NAME = "article"
        private var instance: AppDatabase? = null

        private fun create(context: Context):AppDatabase?{
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME).build()
        }

        public fun getInstance(context: Context): AppDatabase{
            if(instance == null) {
                instance = create(context)
            }
                return instance!!
            }
        }

    abstract fun ArticleDAO(): ArticleDAO
}