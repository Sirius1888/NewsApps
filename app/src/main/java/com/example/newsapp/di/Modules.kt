package com.example.newsapp.di

import com.example.newsapp.helper.SharedPreference
import com.example.newsapp.network.RetrofitClient
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.ui.detail_news.DetailNewsViewModel
import com.example.newsapp.ui.news.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


var newsModule = module {
    single { SharedPreference(get()) }
    single { RetrofitClient().provideNews() }

    factory { NewsRepository(get()) }

    viewModel { NewsViewModel(get()) }
}