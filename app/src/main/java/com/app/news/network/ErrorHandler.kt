package com.app.news.network

import retrofit2.HttpException
import java.io.IOException

class ErrorHandler(private val throwable: Throwable) {

    fun getMessage(): String {
        return when (throwable) {
            is IOException -> "Network Error"
            is HttpException -> throwable.message()
            else-> throwable.message!!
        }
    }
}