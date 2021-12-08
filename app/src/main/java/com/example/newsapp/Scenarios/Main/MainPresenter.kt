package com.example.newsapp.Scenarios.Main

import android.content.Context
import android.util.Log
import com.example.newsapp.Article
import com.example.newsapp.ArticleList
import com.example.newsapp.Entities.AppDatabase
import com.example.newsapp.Network.RetrofitInitializer
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Response


class MainPresenter(val view: MainContract.View, context: Context) : MainContract.Presenter {

    val articleDAO = AppDatabase.getInstance(context).ArticleDAO()


    override fun loadList(context: Context){

        view.showLoading()
        val newsServices = RetrofitInitializer().createNewsServices()
        val call = newsServices.getHeadlines()


        //Pegar o resultado da API usando thread
        call.enqueue(object: retrofit2.Callback<ArticleList>{
            override fun onResponse(call: Call<ArticleList>, response: Response<ArticleList>) {
                view.hideLoading()
                if(response.body() != null){

                    doAsync {
                        articleDAO.deleteAll()
                       val teste = articleDAO.getAll()

                        Log.d("error",teste.toString())
//                        Log.d("error",response.body()!!.articles.toString())
                        var aa = articleDAO.insertAll(response.body()!!.articles as Array<Article>)
//                       Log.d("error",aa as String)
                        uiThread {
                            Log.d("error","chegou no negócio")
                            view.showList(response.body()!!.articles)
                        }
                    }


                }else{
                    doAsync {
                        val local_articles = articleDAO.getAll()

                        uiThread {
                            view.showList(local_articles)
                        }
                    }

//                    view.showMessage("Sem noticias")
                    }
            }

            override fun onFailure(call: Call<ArticleList>, t: Throwable) {
                view.hideLoading()
                doAsync {
                    val local_articles = articleDAO.getAll()

                    uiThread {
                        view.showList(local_articles)
                    }
                }
//                view.showMessage("Falha na conexão")
            }
        })
    }
}