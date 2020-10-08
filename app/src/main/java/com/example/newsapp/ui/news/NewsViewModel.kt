package com.example.newsapp.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.model.Articles
import com.example.newsapp.repository.NewsRepository


class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {

    var isLastPage = false
    var isLoading = false
    var page = 1


    fun fetchEverything(query: String): MutableLiveData<MutableList<Articles>?> {
        page += 1
        return newsRepository.fetchEverything(query, page)
    }

    fun fetchTopHeadlines(): MutableLiveData<MutableList<Articles>?>  {
        page += 1
        return newsRepository.fetchTopHeadlines()
    }

}