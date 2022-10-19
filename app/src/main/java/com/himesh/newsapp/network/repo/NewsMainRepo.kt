package com.himesh.newsapp.network.repo

import android.util.Log
import com.google.gson.Gson
import com.himesh.newsapp.network.response.ResponseNews
import com.himesh.newsapp.utill.NewsAppConstants.LOG_ERROR
import okhttp3.ResponseBody

object NewsMainRepo {



    suspend fun getNews(
        q: String,
        from: String,
        sort: String,
        key: String,
        onResult: (isSuccess: Boolean, results: ResponseNews?, error: String?) -> Unit
    ) {

        try{
            val response = NewsRepo.getNews(q, from, sort, key)
            onResult(
                response.isSuccessful,
                response.body(),
                response.errorBody().toString()
            )
        }
        catch (e: Exception) {
            Log.e(LOG_ERROR,"catch:API: $e")
        }
    }
}

