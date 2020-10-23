package com.example.newsapp.di

import com.example.newsapp.data.db.DatabaseClient
import com.example.newsapp.helper.SharedPreference
import com.example.newsapp.data.network.RetrofitClient
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.ui.detail_news.DetailNewsViewModel
import com.example.newsapp.ui.news.NewsViewModel
import com.example.newsapp.ui.news.fragments.favorite.FavoriteViewModel
import com.example.newsapp.ui.news.fragments.topheadlines.TopHeadlinesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


var newsModule = module {
    single { SharedPreference(get()) }
    single { RetrofitClient().provideNews() }

    single { DatabaseClient().provideDatabase(androidContext()) }
    factory { NewsRepository(get(), get()) }

    viewModel { NewsViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { TopHeadlinesViewModel(get()) }
    viewModel { DetailNewsViewModel(get()) }
}