package com.app.news.network

import com.app.news.feature.topnews.model.TopNews
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NewsService {

    companion object {
        fun getInstance(): NewsService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org")
                .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .build()
            return retrofit.create(NewsService::class.java)
        }
    }

    @GET("/v2/top-headlines?country=in&apiKey=1a38df8182df448cbea6721f8e9638d9")
    suspend fun getTopNews(): Response<TopNews>
}