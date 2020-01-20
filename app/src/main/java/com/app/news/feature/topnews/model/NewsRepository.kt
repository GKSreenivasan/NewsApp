package com.app.news.feature.topnews.model

import com.app.news.network.NewsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository {

    private val newsService = NewsService.getInstance()

    suspend fun getTopNews(): TopNews? {

        return withContext(Dispatchers.Default) {
            var topNews: TopNews? = null
            try {
                val response = newsService.getTopNews().execute()
                if (response.isSuccessful) {
                    topNews = response.body()
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            topNews
        }
    }
}