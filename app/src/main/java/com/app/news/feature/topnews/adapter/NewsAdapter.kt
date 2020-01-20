package com.app.news.feature.topnews.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.news.R
import com.app.news.feature.topnews.model.TopNews
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_news.view.*

class NewsAdapter(val callback: (TopNews.Article) -> Unit) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var dataSource = emptyList<TopNews.Article>()

    fun setDataSource(dataSource: List<TopNews.Article>) {
        this.dataSource = dataSource
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_news, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = dataSource[position]
        holder.bindData(news)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindData(article: TopNews.Article) {
            Glide.with(itemView.context)
                .load(article.urlToImage)
                .into(itemView.image)
            itemView.author.text = article.author
            itemView.title.text = article.title
            itemView.source.text = "Source: " + article.source.name
            itemView.setOnClickListener {
                callback(article)
            }
        }
    }
}