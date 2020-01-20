package com.app.news.feature.topnews.model

class TopNews {

    var status: String?=null
    var totalResults: Int=0
    val articles: List<Article>?=null

    data class Article(
        val author: String,
        val title: String,
        val source: Source,
        val urlToImage: String,
        val url: String
    )

    data class Source(val id: String, val name: String)
}