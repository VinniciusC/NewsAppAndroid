/**
Entidade de um artigo na qual é o objeto de retorno da API e do Banco de Ddos
 */
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