package com.app.news.feature.topnews.model

import com.app.news.network.NewsService

class NewsRepository {

    private val newsService = NewsService.getInstance()

    suspend fun getTopNews(): TopNews?{
        return newsService.getTopNews()
    }
}