package com.example.newsapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.data.model.Articles


@Database(entities = [
    Articles::class],
    version = 1,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articlesDao(): ArticlesDao
}