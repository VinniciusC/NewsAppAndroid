package com.example.newsapp.Entities

import androidx.room.*
import com.example.newsapp.Article

@Dao
interface ArticleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: Array<Article>) : List<Long>

    @Query("SELECT * FROM article")
    fun getAll():List<Article>

    @Query("DELETE FROM article")
    fun deleteAll()
}