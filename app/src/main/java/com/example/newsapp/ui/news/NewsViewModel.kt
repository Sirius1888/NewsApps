package com.example.newsapp.ui.news

import androidx.lifecycle.*
import com.example.newsapp.model.Articles
import com.example.newsapp.model.ResponseBody
import com.example.newsapp.network.Resource
import com.example.newsapp.repository.NewsRepository


class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {

    var isLastPage = false
    var isLoading = false
    var page = 1

    init {
        fetchEverything("bitcoin")
    }

    var articles = MutableLiveData<ResponseBody>()

    fun fetchEverything(query: String): LiveData<Resource<ResponseBody>> {
        page += 1
        return newsRepository.fetchEverything(query, page)
    }

    fun fetchTopHeadlines(): MutableLiveData<MutableList<Articles>?>  {
        page += 1
        return newsRepository.fetchTopHeadlines()
    }

}