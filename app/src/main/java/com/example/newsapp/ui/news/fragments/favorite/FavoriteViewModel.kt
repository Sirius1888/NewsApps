package com.example.newsapp.ui.news.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.model.Articles
import com.example.newsapp.model.ResponseBody
import com.example.newsapp.network.Resource
import com.example.newsapp.repository.NewsRepository


class FavoriteViewModel(val newsRepository: NewsRepository) : ViewModel() {

    fun fetchAllFavorites(): LiveData<List<Articles>> {
        return newsRepository.fetchFavorites()
    }

}