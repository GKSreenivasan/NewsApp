package com.app.news.feature.topnews.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.news.R
import com.app.news.feature.topnews.adapter.NewsAdapter
import com.app.news.feature.topnews.model.Data
import com.app.news.feature.topnews.model.TopNews
import com.app.news.feature.topnews.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_topnews.*

/**
 *  Activity to Show List of Top News
 */
class TopNewsActivity : AppCompatActivity() {

    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topnews)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        refresh.setOnRefreshListener {
            viewModel.fetchTopNews()
        }
        showTopNews()
    }

    private fun showTopNews() {
        viewModel.getTopNews().observe(this@TopNewsActivity, Observer {
            when (it?.getState()) {
                Data.State.LOADING -> {
                    refresh.isRefreshing = true
                }
                Data.State.ERROR -> {
                    refresh.isRefreshing = false
                    showMessage(it.getError() + "")
                }
                Data.State.SUCCESS -> {
                    refresh.isRefreshing = false
                    it.getData()?.articles?.run {
                        val adapter = NewsAdapter(callback)
                        adapter.setDataSource(this)
                        recyclerView.adapter = adapter
                    }
                }
            }
        })
    }

    /**
     *  Callback When Users Clicks List Item
     */
    private val callback: (TopNews.Article) -> Unit = {
        val intent = Intent(this, NewsDetailActivity::class.java)
        intent.putExtra("Url", it.url)
        startActivity(intent)
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}