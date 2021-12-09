/**
Main Activity onde está presente a Recycler View para exibir as notícias
 */
package com.example.newsapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Entities.NewsAdapter
import com.example.newsapp.Scenarios.Main.MainContract
import com.example.newsapp.Scenarios.Main.MainPresenter
import com.example.newsapp.databinding.ActivityMainBinding
import android.widget.Toast as Toast1

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity(), NewsAdapter.OnItemClickListener, MainContract.View{

    //Definindo o layout e o presenter da Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val presenter: MainContract.Presenter = MainPresenter(this, this)
        presenter.loadList(this)
    }

    //Função para inicializar o Adapter
    override fun showList(articles: List<Article>) {

        val adapter = NewsAdapter(this, articles, this)
        //Define o layout e cria uma linha para separar cada item
        val layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(this, layoutManager.orientation)

        //Criação do binding para acessar os elementos do layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Adicionando o adapter a recycler view do XML com as configurações de layout
        binding.rvNews.adapter = adapter
        binding.rvNews.layoutManager = LinearLayoutManager(this)
        binding.rvNews.addItemDecoration(dividerItemDecoration)
    }

    //Função para exibir um Toast
    override fun showMessage(msg: String) {
        Toast1.makeText(this, msg, Toast1.LENGTH_LONG).show()
    }

    //Função que deixa visível a Loading Bar presente no layout
    override fun showLoading() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.LoadingBar.visibility = ProgressBar.VISIBLE
    }

    //Função para deixar invisivel a Loading Bar
    override fun hideLoading() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.LoadingBar.visibility = ProgressBar.GONE
    }

    //Função para criar a Intent de abrir o navegador no link da notícia
    override fun onItemClick(url: String) {
        val openBrowser = Intent(Intent.ACTION_VIEW)
        openBrowser.data = Uri.parse(url)
        startActivity(openBrowser)
    }
}