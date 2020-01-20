package com.app.news.feature.topnews.model

import com.app.news.network.NewsService

class NewsRepository {

    private val newsService = NewsService.getInstance()

    suspend fun getTopNews(): TopNews?{
        try {
            val response=newsService.getTopNews()
            if(response.isSuccessful){
                return response.body()
            }
        } catch (ex:Exception){
            ex.printStackTrace()
        }
        return null
    }
}