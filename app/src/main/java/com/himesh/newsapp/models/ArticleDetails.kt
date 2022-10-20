package com.himesh.newsapp.models

import java.io.Serializable

data class ArticleDetails(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
):Serializable{


    var date:String? = null
    var times:String? = null

 fun getTime():String{

    var publishDate = publishedAt!!.split("T").toTypedArray()
    var publishTime = publishDate[1].split(":").toTypedArray()

     date = publishDate[0]
     times = publishTime[0]+" hours ago"


     return times as String

    }
}