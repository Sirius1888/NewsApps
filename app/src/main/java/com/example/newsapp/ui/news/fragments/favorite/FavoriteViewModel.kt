package com.example.newsapp.ui.news.fragments.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.data.model.Articles
import com.example.newsapp.repository.NewsRepository


class FavoriteViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    fun fetchAllFavorites(): LiveData<List<Articles>> {
        return newsRepository.fetchFavorites()
    }

}