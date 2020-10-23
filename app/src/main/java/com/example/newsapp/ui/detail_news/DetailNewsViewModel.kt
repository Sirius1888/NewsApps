package com.example.newsapp.ui.detail_news

import androidx.lifecycle.ViewModel
import com.example.newsapp.data.model.Articles
import com.example.newsapp.repository.NewsRepository

class DetailNewsViewModel(val newsRepository: NewsRepository) : ViewModel() {

    fun insertEverything(articles: Articles) {
        newsRepository.insertNewNews(articles)
    }

}