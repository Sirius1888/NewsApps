package com.example.newsapp.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.data.model.Articles
import com.example.newsapp.data.model.ResponseBody
import com.example.newsapp.data.network.Resource
import com.example.newsapp.repository.NewsRepository


class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    var isLastPage = false
    var isLoading = false
    var page = 1

    init {
        fetchEverything("bitcoin")
    }

    var articles: MutableLiveData<MutableList<Articles>> = MutableLiveData()

    fun fetchEverything(query: String): LiveData<Resource<ResponseBody>> {
        page += 1
        return newsRepository.fetchEverything(query, page)
    }

    fun insertEverything(articles: Articles) {
        newsRepository.insertNewNews(articles)
    }

}