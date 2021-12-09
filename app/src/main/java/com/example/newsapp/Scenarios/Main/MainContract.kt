/**
Contract com a interface da view e presenter para a execução das funções
 */
package com.example.newsapp.Scenarios.Main

import android.content.Context
import com.example.newsapp.Article

interface MainContract {

    interface View{
        fun showMessage(msg: String)
        fun showList(articles: List<Article>)
        fun showLoading()
        fun hideLoading()
        fun onItemClick(url: String)
    }

    interface Presenter{
        fun loadList(context: Context)
    }
}