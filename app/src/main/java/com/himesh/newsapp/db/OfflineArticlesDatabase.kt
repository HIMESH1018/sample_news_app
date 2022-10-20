package com.himesh.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [OfflineArticles::class],version = 1)
abstract class OfflineArticlesDatabase: RoomDatabase() {

    abstract val articlesDAO: ArticlesDAO

    companion object{

        @Volatile
        private var INSTANCE : OfflineArticlesDatabase? = null

        fun getInstance(context:Context):OfflineArticlesDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        OfflineArticlesDatabase::class.java,
                        "offline_articles_database"
                    ).build()
                }
                return instance
            }
        }
    }
}