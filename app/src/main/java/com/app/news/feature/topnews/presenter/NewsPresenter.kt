package com.app.news.feature.topnews.presenter

import com.app.news.feature.topnews.model.NewsRepository
import com.app.news.feature.topnews.model.TopNews

class NewsPresenter(private val newsRepository: NewsRepository) {

    suspend fun getTopNews(): TopNews? {
        return newsRepository.getTopNews()
    }
}