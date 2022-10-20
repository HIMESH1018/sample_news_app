package com.dishserve.app.rest.api

import WebApiRequestInterceptor
import com.himesh.newsapp.network.api.APIClientService
import com.himesh.newsapp.network.api.NewsApiRestFactory
import com.himesh.newsapp.network.api.RestFactoryType

abstract class ApiRestFactory {
    abstract fun apiInstance(headerInterceptor: WebApiRequestInterceptor): APIClientService

    companion object { // check the BASE URL TYPE
        fun create(type : RestFactoryType): ApiRestFactory =
            when (type) {
                RestFactoryType.NEWS -> NewsApiRestFactory
                else -> throw IllegalArgumentException()
            }
    }
}