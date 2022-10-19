package com.himesh.newsapp.network.api

import com.himesh.newsapp.network.response.ResponseNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIClientService {

    @GET("everything")
    suspend fun getData(
        @Query("q") q:String,
        @Query("from") from: String,
        @Query("sortBy") sort: String,
        @Query("apiKey") key: String
    ): Response<ResponseNews>

}