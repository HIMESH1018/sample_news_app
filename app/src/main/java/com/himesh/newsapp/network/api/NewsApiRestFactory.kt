package com.himesh.newsapp.network.api

import WebApiRequestInterceptor
import com.dishserve.app.rest.api.ApiRestFactory
import com.google.gson.GsonBuilder
import com.himesh.newsapp.BuildConfig
import com.himesh.newsapp.utill.NewsAppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NewsApiRestFactory: ApiRestFactory() {

    private var url: String = BuildConfig.SERVER_ENDPOINT

    override fun apiInstance(headerInterceptor: WebApiRequestInterceptor): APIClientService {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(httpLoggingInterceptor)
        builder.addInterceptor(headerInterceptor)
        builder.readTimeout(NewsAppConstants.REQUEST_READ_WRITE_TIME_OUT, TimeUnit.SECONDS)
        builder.connectTimeout(NewsAppConstants.REQUEST_CONNECT_TIME_OUT, TimeUnit.SECONDS)
        builder.writeTimeout(NewsAppConstants.REQUEST_READ_WRITE_TIME_OUT, TimeUnit.SECONDS)

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .client(builder.build())
            .build()

        return retrofit.create(APIClientService::class.java)
    }
}