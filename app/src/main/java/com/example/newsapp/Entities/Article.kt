package com.example.newsapp
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article(
                   var author: String?,
                   var title: String?,
                   var description: String?,
                   var url: String?,
                   var urlToImage: String?,
                   var date: String?,
                   var content: String?,
                   @PrimaryKey(autoGenerate = true)
                   var id: Int = 0)