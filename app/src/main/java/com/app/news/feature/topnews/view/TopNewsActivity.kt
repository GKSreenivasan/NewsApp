package com.app.news.feature.topnews.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.news.R
import com.app.news.feature.topnews.adapter.NewsAdapter
import com.app.news.feature.topnews.model.NewsRepository
import com.app.news.feature.topnews.model.TopNews
import com.app.news.feature.topnews.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_topnews.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class TopNewsActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + mJob

    private lateinit var mJob: Job
    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topnews)

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        //recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mJob = Job()
        refresh.setOnRefreshListener {
            loadTopNews()
        }

        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        viewModel.setRepository(NewsRepository())
        subscribeTopNews()
        loadTopNews()
    }

    private fun subscribeTopNews() {
        viewModel.getTopNews().observe(this, Observer {
            if (it == null || it.status.equals("error", true)) {
                showMessage("Something Went Wrong!")
                return@Observer
            }
            if (it.status.equals("Ok", true)) {
                it.articles?.run {
                    val adapter = NewsAdapter(callback)
                    adapter.setDataSource(this)
                    recyclerView.adapter = adapter
                }
            }
        })
    }

    private fun loadTopNews() = launch(Dispatchers.Main) {
        refresh.isRefreshing = true
        viewModel.loadTopNews()
        refresh.isRefreshing = false
    }

    private val callback: (TopNews.Article) -> Unit = {
        val intent = Intent(this, NewsDetailActivity::class.java)
        intent.putExtra("Url", it.url)
        startActivity(intent)
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.getTopNews().removeObservers(this)
        mJob.cancel()
    }
}