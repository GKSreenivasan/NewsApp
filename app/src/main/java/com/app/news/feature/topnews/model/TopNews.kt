package com.app.news.feature.topnews.model

/**
 *  Simple Data Class For Top News JSON Response
 */
class TopNews {

    var status: String=""
    var message: String=""
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