/**
Interface para acesso a API
 */
package com.example.newsapp.Network

import com.example.newsapp.Database.ArticleList
import retrofit2.Call
import retrofit2.http.GET

interface NewsServices {

    //Definição da API key de maneira estática
    companion object{
        private const val API_KEY = "apiKey=96b3df5ec358422096f01dc94bd20864"
    }

    //Método da API para buscar pelo top headlines das notícias do Brasil
    @GET("top-headlines?country=br&$API_KEY")
    fun getHeadlines() : Call<ArticleList>
}