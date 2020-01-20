package com.app.news.feature.topnews.presenter

import com.app.news.feature.topnews.model.NewsRepository
import com.app.news.feature.topnews.model.TopNews
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NewsPresenterTest {

    @Mock
    private lateinit var repository: NewsRepository
    private var presenter: NewsPresenter? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NewsPresenter(repository)
    }

    @Test
    fun getTopNews() {
        runBlocking {
            val topNews = TopNews()
            topNews.status = "Ok"
            Mockito.`when`(repository.getTopNews()).thenReturn(topNews)
            val result = presenter?.getTopNews()
            Assert.assertEquals("Ok", result?.status)
        }
    }

    @After
    fun clean() {
        presenter = null
    }
}