package com.example.crud_mvvm.viewModels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.himesh.newsapp.db.ArticleRepository
import com.himesh.newsapp.ui.viewmodels.OfflineArticlesViewModel

class OfflineArticlesViewModelFactory(private val repository: ArticleRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OfflineArticlesViewModel::class.java)) {
            return OfflineArticlesViewModel(repository) as T
        }
        throw IllegalAccessException("Unknown View Model Class")
    }

}