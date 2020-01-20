package com.app.news.feature.topnews.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.news.R
import kotlinx.android.synthetic.main.activity_news_details.*

class NewsDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        back.setOnClickListener {
            finish()
        }
        web.loadUrl(intent.getStringExtra("Url"))
    }
}