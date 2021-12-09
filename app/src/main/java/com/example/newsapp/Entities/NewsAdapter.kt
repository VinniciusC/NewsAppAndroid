/**
Adapter para transformar o Array do objeto Artigo em itens da Recycler View
 */
package com.example.newsapp.Entities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp.Article
import com.example.newsapp.R

class NewsAdapter(val context: Context,
                  val articles: List<Article>,
                  private val listener: OnItemClickListener)
    : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    //Criação do View Holder para inflar o item que será utilizado na Recycler View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_item,
            parent, false)

        return ViewHolder(itemView)
    }

    //Retorna o número de artigos na lista
    override fun getItemCount() = articles.size

    //Insere os dados do artigo nos componentes do layout
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //Colocando os dados nas TextViews
        holder.Title.text = articles[position].title
        holder.Description.text = articles[position].description

        //Exibição de imagem com Glide
        //Definindo a imagem padrão caso não exista
        val sharedOptions: RequestOptions = RequestOptions()
            .placeholder(R.drawable.news_icon)
            .centerCrop()
        //Insserindo a imagem no ImageView
        Glide.with(context)
            .load(articles[position].urlToImage)
            .apply(sharedOptions)
            .into(holder.ImgNews)

    }

    //Objeto no qual representa cada item da Recycler View
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener{

        val ImgNews: ImageView = itemView.findViewById(R.id.imgNews)
        val Title: TextView = itemView.findViewById(R.id.Title)
        val Description: TextView = itemView.findViewById(R.id.Description)

        init {
            itemView.setOnClickListener(this)
        }

    //Função de click do artigo para redirecionar ao URL
    override fun onClick(v: View?) {
        val position: Int = adapterPosition
        articles[position].url?.let { listener.onItemClick(it) }
    }
    }
    interface OnItemClickListener{
        fun onItemClick(url: String)
    }
    }