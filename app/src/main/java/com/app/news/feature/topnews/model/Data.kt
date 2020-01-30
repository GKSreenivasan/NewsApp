package com.app.news.feature.topnews.model

open class Data<T> {

    private var state: State = State.LOADING
    private var data: T? = null
    private var error: String? = "Something Went Wrong!"

    fun setData(data: T) {
        this.data = data
        this.state = State.SUCCESS
    }

    fun setError(error: String?) {
        this.error = error
        this.state = State.ERROR
    }

    fun getState(): State {
        return state
    }

    fun getData(): T? {
        return data
    }

    fun getError(): String? {
        return this.error
    }

    enum class State {
        LOADING,
        ERROR,
        SUCCESS
    }
}