package com.example.newsapp.Network

import com.example.newsapp.ArticleList
import retrofit2.Call
import retrofit2.http.GET

interface NewsServices {

    companion object{
        private const val API_KEY = "apiKey=96b3df5ec358422096f01dc94bd20864"
    }

    @GET("top-headlines?country=br&$API_KEY")
    fun getHeadlines() : Call<ArticleList>
}