package com.app.news.feature.topnews.repository

import com.app.news.database.NewsDAO
import com.app.news.feature.topnews.model.TopNewsData
import com.app.news.network.ErrorHandler
import com.app.news.network.NewsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 *  This Class is used to Fetch Data from Network Service and Save It In Database
 */
class NewsRepositoryImp(private val newsDAO: NewsDAO, private val newsService: NewsService) : NewsRepository {

    @ExperimentalCoroutinesApi
    override suspend fun getTopNews(): Flow<TopNewsData> = flow {
        val topNewsData = TopNewsData()
        emit(topNewsData)
        try {
            newsDAO.getTopNews()?.run {
                topNewsData.setData(this)
                emit(topNewsData)
            }
            newsService.getHeadlines()?.run {
                newsDAO.saveTopNews(this)
                topNewsData.setData(this)
            }
        } catch (throwable: Throwable) {
            topNewsData.setError(ErrorHandler(throwable).getMessage())
        }
        emit(topNewsData)
    }.flowOn(Dispatchers.IO)
}