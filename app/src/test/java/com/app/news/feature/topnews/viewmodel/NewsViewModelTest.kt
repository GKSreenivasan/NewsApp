package com.app.news.feature.topnews.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.app.news.feature.topnews.model.NewsRepository
import com.app.news.feature.topnews.model.TopNews
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class NewsViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: NewsRepository
    private lateinit var viewModel: NewsViewModel
    @Mock
    lateinit var observer: Observer<TopNews>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = NewsViewModel()
        viewModel.setRepository(repository)
        viewModel.getTopNews().observeForever(observer)
    }

    @Test
    fun loadTopNews() {
        runBlocking {
            val expected = TopNews()
            Mockito.`when`(repository.getTopNews()).thenReturn(expected)
            viewModel.loadTopNews()
            val captor = ArgumentCaptor.forClass(TopNews::class.java)
            captor.run {
                verify(observer, times(1)).onChanged(capture())
                assertEquals(expected, value)
            }
        }
    }
}