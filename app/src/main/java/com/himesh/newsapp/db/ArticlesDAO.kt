package com.himesh.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArticlesDAO {

    @Insert
    suspend fun insertArticle(articles: OfflineArticles):Long

    @Query("DELETE FROM articles_data_table WHERE article_ID = :articleID")
    suspend fun deleteSingleArticle(articleID:Int)

    @Query("DELETE FROM articles_data_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM articles_data_table")
    fun getAllArticles(): LiveData<List<OfflineArticles>>
}