package com.himesh.newsapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himesh.newsapp.R
import com.himesh.newsapp.models.Article
import com.himesh.newsapp.network.repo.NewsMainRepo
import com.himesh.newsapp.network.repo.NewsRepo
import com.himesh.newsapp.network.response.ResponseNews
import com.himesh.newsapp.utill.NewsAppConstants
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val Q:String = "tesla"
    val FROM:String = "2022-10-18" // pdf given date data cannot access  -> too old
    private val SORT:String = "publishedAt"
    private val API_KEY = "71f82bb721214dd88ce601a8247d98d0"

    val mProgress = MutableLiveData<Boolean>().apply { value = false }
    val mNewsDataSuccessObserver = MutableLiveData<Int>()

    val mNetworkConnected = MutableLiveData<Boolean>().apply { value = false }
    val mArticles = MutableLiveData<ArrayList<Article>>()




    fun getNewsData() {
        try {
            mArticles.value?.clear()
            mProgress.value = true
            viewModelScope.launch {
                NewsMainRepo.getNews(
                    Q,
                    FROM,
                    SORT,
                    API_KEY) { isSuccess, results, error ->
                    mProgress.value = false
                    processGetNewsData(isSuccess, results, error)
                }
            }
        } catch (e: Exception) {

            Log.e(NewsAppConstants.LOG_ERROR,"API_Access_Error: $e")
        }
    }

    private fun processGetNewsData(success: Boolean, results: ResponseNews?, error: String?) {

        if(results != null){

            if(results.status == NewsAppConstants.SUCCESS){

                mArticles.postValue(results.articles as ArrayList<Article>?)

            }else{
                mNewsDataSuccessObserver.value = R.string.api_empty_data
            }

        }else{
            mNewsDataSuccessObserver.value = R.string.api_empty_data
        }

    }


}