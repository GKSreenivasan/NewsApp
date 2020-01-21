package com.app.news.feature.topnews.model

import com.app.news.database.NewsDAO
import com.app.news.network.NewsService

class NewsRepository(private val newsDAO: NewsDAO,private val newsService: NewsService) {

    suspend fun getTopNews(): TopNews?{
        try {
            val response=newsService.getTopNews()
            if(response.isSuccessful){
                newsDAO.saveTopNews(response.body()!!)
                return response.body()
            }
        } catch (ex:Exception){
            ex.printStackTrace()
        }
        return newsDAO.getTopNews()
    }
}