package com.app.news.database

import com.app.news.feature.topnews.model.TopNews

class NewsDAO {

    private var news: TopNews?=null

    fun saveTopNews(news: TopNews){
        this.news=news
    }

    fun getTopNews(): TopNews? {
        return news
    }
}