package com.example.newsapp.Entities

import android.content.Context
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.newsapp.Article
import com.example.newsapp.databinding.ActivityMainBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.newsapp.R
import com.example.newsapp.utils.GlideModule
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp.MainActivity

private lateinit var binding: ActivityMainBinding

class NewsAdapter(val context: Context,
                  val articles: List<Article>,
                  private val listener: OnItemClickListener)
    : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_item,
            parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount() = articles.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.Title.text = articles[position].title
        holder.Description.text = articles[position].description

        val sharedOptions: RequestOptions = RequestOptions()
            .placeholder(R.drawable.news_icon)
            .centerCrop()

        Glide.with(context)
            .load(articles[position].urlToImage)
            .apply(sharedOptions)
            .into(holder.ImgNews)



    }
//    Linha única da lista
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener{

        val ImgNews: ImageView = itemView.findViewById(R.id.imgNews)
        val Title: TextView = itemView.findViewById(R.id.Title)
        val Description: TextView = itemView.findViewById(R.id.Description)

        init {
            itemView.setOnClickListener(this)
        }

    override fun onClick(v: View?) {
        val position: Int = adapterPosition
        articles[position].url?.let { listener.onItemClick(it) }

    }
}
    interface OnItemClickListener{
        fun onItemClick(url: String)
    }
}