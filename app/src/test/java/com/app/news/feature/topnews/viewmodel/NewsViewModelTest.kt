package com.app.news.feature.topnews.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.news.feature.topnews.model.Data
import com.app.news.feature.topnews.model.TopNews
import com.app.news.feature.topnews.model.TopNewsData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

class NewsViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: NewsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = NewsViewModel()
    }

    @Test
    fun getTopNews_Emits_Loading() {
        val topNewsData = TopNewsData()
        viewModel.setValue(topNewsData)
        assertEquals(Data.State.LOADING, viewModel.getTopNews().value?.getState())
    }

    @Test
    fun getTopNews_Throws_Error() {
        val topNews = TopNews()
        topNews.status = "error"

        val topNewsData = TopNewsData()
        topNewsData.setError("Network Error")

        viewModel.setValue(topNewsData)
        assertEquals("Network Error", viewModel.getTopNews().value?.getError())
    }

    @Test
    fun getTopNews_Returns_Success() {
        val topNews = TopNews()
        topNews.status = "ok"

        val topNewsData = TopNewsData()
        topNewsData.setData(topNews)

        viewModel.setValue(topNewsData)
        assertEquals("ok", viewModel.getTopNews().value?.getData()?.status)
    }

}