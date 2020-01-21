package com.app.news.feature.topnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.news.feature.topnews.model.TopNews
import com.app.news.feature.topnews.repository.NewsRepository

class NewsViewModel : ViewModel() {

    private val topNewsData = MutableLiveData<TopNews>()

    private lateinit var repository: NewsRepository

    fun setRepository(repository: NewsRepository) {
        this.repository = repository
    }

    suspend fun loadTopNews() {
        topNewsData.value=repository.getTopNews()
    }

    fun getTopNews(): LiveData<TopNews> {
        return topNewsData
    }

}