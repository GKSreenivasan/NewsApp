package com.app.news.feature.topnews.viewmodel

import androidx.lifecycle.*
import com.app.news.database.NewsDAO
import com.app.news.feature.topnews.model.TopNewsData
import com.app.news.feature.topnews.repository.NewsRepository
import com.app.news.feature.topnews.repository.NewsRepositoryImp
import com.app.news.network.NewsService
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private var topNewsLiveData = MutableLiveData<TopNewsData>()

    private var repository: NewsRepository = NewsRepositoryImp(NewsDAO(), NewsService.getInstance())

    init {
        fetchTopNews()
    }

    fun getTopNews(): LiveData<TopNewsData> {
        return topNewsLiveData
    }

    fun fetchTopNews() {
        viewModelScope.launch {
            repository.getTopNews().asLiveData().observeForever {
                setValue(it)
            }
        }
    }

    fun setValue(data: TopNewsData) {
        this.topNewsLiveData.value = data
    }
}