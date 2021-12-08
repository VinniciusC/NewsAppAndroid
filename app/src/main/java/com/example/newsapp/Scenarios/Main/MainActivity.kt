package com.example.newsapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Article
import com.example.newsapp.Entities.NewsAdapter
import com.example.newsapp.Scenarios.Main.MainContract
import com.example.newsapp.Scenarios.Main.MainPresenter
import com.example.newsapp.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity(), NewsAdapter.OnItemClickListener, MainContract.View{

//    val a1 = Article(
//        author = "johnu78",
//        title = "Where is Litecoin Going?",
//        description = "I’ve been a fan of Litecoin since I first learned about it in 2013. The coin has many good fundamentals to it and I believe that Charlie Lee, the coin’s creator did things right as far as how he created the coin and how he worked to develop it. Towards the en…",
//        url = "https://cointrader21.wordpress.com/2018/11/08/where-is-litecoin-going/",
//        urlToImage = "https://cointrader21.files.wordpress.com/2018/11/litecoinlogo.png",
//        date = "2018-11-08T17:35:17Z",
//        content = "I’ve been a fan of Litecoin since I first learned about it in 2013. The coin has many good fundamentals to it and I believe that Charlie Lee, the coin’s creator did things right as far as how he created the coin and how he worked to develop it. Towards the en… [+1311 chars]"
//    )
//
//    val a2 = Article(
//        author = "Yashu Gola",
//        title = "Bitcoin Analysis: BTC Faces Bearish Reversal as Dollar Strengthens",
//        description = "The bitcoin-to-dollar exchange rate has dipped close to 1 percent on Thursday, now trading at 6470-fiat. The outcome of the US midterm elections with Democrats sweeping a sharp win in the House had certainly shaken the US dollar yesterday. The greenback never…",
//        url = "https://www.ccn.com/bitcoin-analysis-btc-faces-bearish-reversal-as-dollar-strengthens/",
//        urlToImage = "https://www.ccn.com/wp-content/uploads/2018/09/bitcoin-dominance-flex-muscles.jpg",
//        date = "2018-11-08T17:32:48Z",
//        content = "The bitcoin-to-dollar exchange rate has dipped close to 1 percent on Thursday, now trading at 6470-fiat. The outcome of the US midterm elections with Democrats sweeping a sharp win in the House had certainly shaken the US dollar yesterday. The greenback never… [+2862 chars]"
//    )
//
//    val testList = listOf(a1, a2)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val presenter: MainContract.Presenter = MainPresenter(this, this)
        presenter.loadList(this)
    }

    override fun showList(articles: List<Article>) {

        val adapter = NewsAdapter(this, articles, this)
        val layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(this, layoutManager.orientation)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvNews.adapter = adapter
        binding.rvNews.layoutManager = LinearLayoutManager(this)
        binding.rvNews.addItemDecoration(dividerItemDecoration)
    }

    override fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.LoadingBar.visibility = ProgressBar.VISIBLE
    }

    override fun hideLoading() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.LoadingBar.visibility = ProgressBar.GONE
    }

    override fun onItemClick(url: String) {
        val openBrowser = Intent(Intent.ACTION_VIEW)
        openBrowser.data = Uri.parse(url)
        startActivity(openBrowser)
    }
}