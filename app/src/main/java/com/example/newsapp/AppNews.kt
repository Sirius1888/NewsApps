package com.example.newsapp

import android.app.Application
import com.example.newsapp.di.newsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppNews : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppNews)
            modules(newsModule)
        }
    }

}