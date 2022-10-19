package com.himesh.newsapp.db


class ArticleRepository(private val dao:ArticlesDAO) {

    val article = dao.getAllArticles()

    suspend fun insert(articles: OfflineArticles){

        dao.insertArticle(articles)
    }

    suspend fun deleteSingleArticle(articleID:Int){

        dao.deleteSingleArticle(articleID)
    }

    suspend fun deleteAll(){

        dao.deleteAll()
    }
}