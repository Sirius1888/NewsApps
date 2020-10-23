package com.example.newsapp.db

import android.content.Context
import androidx.room.Room

class DatabaseClient {

    fun provideDatabase(context: Context): AppDatabase {
       return Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }

}