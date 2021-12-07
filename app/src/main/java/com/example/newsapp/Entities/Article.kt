package com.example.newsapp
import com.google.gson.annotations.SerializedName

data class Article (val author: String,
                    val title: String,
                    val description: String,
                    val url: String,
                    val urlToImage: String,
                    @SerializedName("publishedAt")
                    val date: String,
                    val content: String)