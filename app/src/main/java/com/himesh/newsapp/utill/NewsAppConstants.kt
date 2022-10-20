package com.himesh.newsapp.utill

import android.content.Context
import android.widget.Toast

object NewsAppConstants {

    const val LOG_ERROR = "NEWS_ERROR"
    const val LOG_DATA_CHECK = "NEWS_DATA"
    const val LOG_NET_CHECK = "INTERNET_STATUS"


    const val AUTH_TOKEN_CONTENT_TYPE = "Content-Type"
    const val AUTH_TOKEN_CONTENT_TYPE_VALUE_JSON = "application/json"
    const val AUTH_CONNECTION_TYPE = "Connection"
    const val AUTH_CONNECTION_TYPE_VALUE = "keep-alive"


    //Time Outs
    const val REQUEST_READ_WRITE_TIME_OUT: Long = 45
    const val REQUEST_CONNECT_TIME_OUT: Long = 45


    //API RESPONSE STATUS

    const val SUCCESS = "ok"


    //Intent
    val TEXT_INTENT = "text/plain"
    val INTENT_PASS_KEY = "news"


    fun displayMessage(mContext:Context,text:String){
        val toast = Toast.makeText(
            mContext,
            text,
            Toast.LENGTH_SHORT
        )
        toast.show()
    }
}
