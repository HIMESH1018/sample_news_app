package com.himesh.newsapp.ui.viewmodels

import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himesh.newsapp.db.ArticleRepository
import com.himesh.newsapp.db.OfflineArticles
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OfflineArticlesViewModel(private val repository: ArticleRepository): ViewModel(),
    Observable {

    val offlineArticles = repository.article


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }




    fun insert(articles: OfflineArticles): Job = viewModelScope.launch {
        repository.insert(articles)
    }

    fun deleteSingleArticle(articlesID:Int):Job = viewModelScope.launch {
        repository.deleteSingleArticle(articlesID)
    }

    fun deleteAll():Job = viewModelScope.launch {
        repository.deleteAll()
    }
}