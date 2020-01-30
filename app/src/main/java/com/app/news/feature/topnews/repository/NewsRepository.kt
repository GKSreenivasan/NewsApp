package com.app.news.feature.topnews.repository

import com.app.news.feature.topnews.model.TopNewsData
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getTopNews(): Flow<TopNewsData>
}