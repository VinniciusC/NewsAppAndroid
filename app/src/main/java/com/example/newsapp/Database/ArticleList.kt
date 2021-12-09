/**
Entidade para representar o retorno da API. Uma lista de artigos
 */
package com.example.newsapp.Database

import com.example.newsapp.Article

data class ArticleList (val articles: List<Article>) {
}