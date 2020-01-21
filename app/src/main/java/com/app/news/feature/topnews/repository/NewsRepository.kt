package com.app.news.feature.topnews.repository

import com.app.news.feature.topnews.model.TopNews

interface NewsRepository {

    suspend fun getTopNews(): TopNews?
}