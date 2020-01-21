package com.app.news.feature.topnews.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.app.news.R
import kotlinx.android.synthetic.main.activity_news_details.*

/**
 * Activity to Show News Content in WebView
 */
class NewsDetailActivity: AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        back.setOnClickListener {
            finish()
        }
        web.webViewClient = object: WebViewClient() {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }
        }
        web.settings.javaScriptEnabled=true
        web.loadUrl(intent.getStringExtra("Url"))
    }
}