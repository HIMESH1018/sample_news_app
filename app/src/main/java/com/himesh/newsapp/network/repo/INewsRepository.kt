package com.himesh.newsapp.network.repo

import com.himesh.newsapp.network.response.ResponseNews
import retrofit2.Response

interface INewsRepository {

    suspend fun getNews(
        q: String,
        from: String,
        sort: String,
        key: String,
    ): Response<ResponseNews>?
}