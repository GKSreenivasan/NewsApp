package com.app.news.feature.topnews.repository

import com.app.news.database.NewsDAO
import com.app.news.feature.topnews.model.TopNews
import com.app.news.network.NewsService

/**
 *  This Class is used to Fetch Data from Network Service and Save It In Database
 */
class NewsRepositoryImp(private val newsDAO: NewsDAO, private val newsService: NewsService) : NewsRepository {

    override suspend fun getTopNews(): TopNews? {
        try {
            val response = newsService.getTopNews()
            if (response.isSuccessful) {
                newsDAO.saveTopNews(response.body()!!)
                return response.body()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return newsDAO.getTopNews()
    }
}