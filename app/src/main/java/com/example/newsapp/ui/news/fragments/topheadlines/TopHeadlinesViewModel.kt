package com.example.newsapp.ui.news.fragments.topheadlines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.data.model.ResponseBody
import com.example.newsapp.data.network.Resource
import com.example.newsapp.repository.NewsRepository

class TopHeadlinesViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    var isLastPage = false
    var isLoading = false
    var page = 1

    var articles = MutableLiveData<Resource<ResponseBody>?>()

    init {
        fetchTopHeadlines()
    }

    fun fetchTopHeadlines(): LiveData<Resource<ResponseBody>> {
        page += 1
        return newsRepository.fetchTopHeadlines()
    }

}