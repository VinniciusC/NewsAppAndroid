/**
Inicializador do Retrofit com as informações da API
 */
package com.example.newsapp.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    //Definindo o URL da API e o conversor de Gson
    val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun createNewsServices(): NewsServices = retrofit.create(NewsServices::class.java)
}