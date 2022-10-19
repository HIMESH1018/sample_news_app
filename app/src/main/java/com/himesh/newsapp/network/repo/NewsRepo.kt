package com.himesh.newsapp.network.repo

import com.himesh.newsapp.network.api.APIClient
import com.himesh.newsapp.network.api.RestFactoryType
import com.himesh.newsapp.network.response.ResponseNews
import retrofit2.Response

object NewsRepo: INewsRepository {

    private val mAPIClient = APIClient(RestFactoryType.NEWS)



    override suspend fun getNews(
        q: String,
        from: String,
        sort: String,
        key: String,
    ): Response<ResponseNews> {
        return mAPIClient.getNews(q, from, sort,key)
    }

}