/**
Interface para criar o DAO da biblioteca Room do banco de dados
 */
package com.example.newsapp.Database

import androidx.room.*
import com.example.newsapp.Article

@Dao
interface ArticleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<Article>) : List<Long>

    @Query("SELECT * FROM article")
    fun getAll():List<Article>

    @Query("DELETE FROM article")
    fun deleteAll()
}