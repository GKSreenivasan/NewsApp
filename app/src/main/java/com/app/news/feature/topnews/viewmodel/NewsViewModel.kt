package com.app.news.feature.topnews.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.news.feature.topnews.model.NewsRepository
import com.app.news.feature.topnews.model.TopNews

class NewsViewModel : ViewModel() {

    private val topNewsData = MutableLiveData<TopNews>()

    private val repository = NewsRepository()

    suspend fun loadTopNews(){
        topNewsData.value = repository.getTopNews()
    }

    fun getTopNews(): MutableLiveData<TopNews> {
        return topNewsData
    }
}