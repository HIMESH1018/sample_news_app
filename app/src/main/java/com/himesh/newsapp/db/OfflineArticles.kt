package com.himesh.newsapp.db

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.himesh.newsapp.models.Article

@Entity(tableName = "articles_data_table")
data class OfflineArticles(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "article_ID")
    var id: Int,

    @Nullable
    @ColumnInfo(name = "article_title")
    var title:String?,

    @Nullable
    @ColumnInfo(name = "article_author")
    var author:String?,

    @Nullable
    @ColumnInfo(name = "article_url")
    var url:String?,

    @Nullable
    @ColumnInfo(name = "article_image")
    var image:String?,

    @Nullable
    @ColumnInfo(name = "article_date")
    var date:String?,

    @Nullable
    @ColumnInfo(name = "article_content")
    var content:String?,

    @Nullable
    @ColumnInfo(name = "article_description")
    var description:String?
)
