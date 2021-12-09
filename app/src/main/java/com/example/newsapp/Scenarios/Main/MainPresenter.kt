/**
Presenter onde é feita a requisição da API e também do Banco de dados local
 */
package com.example.newsapp.Scenarios.Main

import android.content.Context
import com.example.newsapp.Database.ArticleList
import com.example.newsapp.Database.AppDatabase
import com.example.newsapp.Network.RetrofitInitializer
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Response


class MainPresenter(val view: MainContract.View, context: Context) : MainContract.Presenter {

    //Criação da Instância do Banco de Dados
    val articleDAO = AppDatabase.getInstance(context).ArticleDAO()

    //Função para obter os dados via API ou Banco de Dados
    override fun loadList(context: Context){

        //Atica a Loading Bar
        view.showLoading()

        //Inicializa o Retofit e chama o método para obter os Headlines
        val newsServices = RetrofitInitializer().createNewsServices()
        val call = newsServices.getHeadlines()

        //Pegar o resultado da API usando thread
        call.enqueue(object: retrofit2.Callback<ArticleList>{
            override fun onResponse(call: Call<ArticleList>, response: Response<ArticleList>) {

                //Desativa a Loading Bar
                view.hideLoading()

                //Caso tenha retorno da API, os dados do banco local são deletados e inseridos
                //os da última requisição, para uso offline

                if(response.body() != null){

                    //Executa as operações em outra thread
                    doAsync {
                        articleDAO.deleteAll()
                        articleDAO.insertAll(response.body()!!.articles)

                        //Chama a função de exibir a lista após a conclusão das operações do BD
                        uiThread {
                            view.showList(response.body()!!.articles)
                        }
                    }
                //Caso não tenha retorno, carrega as notícias da memória do aparelho
                }else{
                    doAsync {
                        val local_articles = articleDAO.getAll()

                        uiThread {
                            view.showList(local_articles)
                        }
                    }
                    }
            }

            //Caso tenha falha na execução, são carregadas as notícias da memória do aparelho
            override fun onFailure(call: Call<ArticleList>, t: Throwable) {
                view.hideLoading()
                doAsync {
                    val local_articles = articleDAO.getAll()
                    uiThread {
                        view.showMessage("Falha na conexão. Exibindo notícias salvas na memória")
                        view.showList(local_articles)
                    }
                }

            }
        })
    }
}