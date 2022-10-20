package com.himesh.newsapp.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himesh.newsapp.R
import com.himesh.newsapp.db.OfflineArticles
import com.himesh.newsapp.models.Article
import com.himesh.newsapp.models.ArticleDetails
import com.himesh.newsapp.network.repo.NewsMainRepo
import com.himesh.newsapp.network.repo.NewsRepo
import com.himesh.newsapp.network.response.ResponseNews
import com.himesh.newsapp.utill.NewsAppConstants
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ArticleDetailsViewModel() : ViewModel() {


    var articleDetails = MutableLiveData<ArticleDetails>()



    fun saveSingleArticles(details: ArticleDetails) {

        articleDetails.postValue(details)

        Log.e("SavedDAta: ",""+articleDetails.value +"Artcile: "+details)
    }


    fun getData():MutableLiveData<ArticleDetails>{

        return articleDetails
    }


}