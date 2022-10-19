package com.himesh.newsapp.network.api

import WebApiRequestInterceptor
import com.dishserve.app.rest.api.ApiRestFactory
import com.himesh.newsapp.network.response.ResponseNews
import retrofit2.Response


class APIClient(factoryType : RestFactoryType) {

    private var mNewsAPI: APIClientService = ApiRestFactory.create(factoryType).apiInstance(WebApiRequestInterceptor())

    suspend fun getNews(q:String,from:String,sort:String,key:String): Response<ResponseNews> {
        return mNewsAPI.getData(q,from,sort,key)
    }
}