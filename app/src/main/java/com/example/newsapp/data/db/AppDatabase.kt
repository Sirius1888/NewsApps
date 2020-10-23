package com.example.newsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.model.Articles
import com.example.newsapp.model.ArticlesDao


@Database(entities = [
    Articles::class],
    version = 1,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articlesDao(): ArticlesDao
}