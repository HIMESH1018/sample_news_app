package com.himesh.newsapp.network.response

import com.himesh.newsapp.models.Article

data class ResponseNews(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)