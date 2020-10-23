package com.example.newsapp.repository

import com.example.newsapp.data.network.NewsApi
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.liveData
import com.example.newsapp.Constants
import com.example.newsapp.data.db.AppDatabase
import com.example.newsapp.data.model.Articles
import com.example.newsapp.data.network.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsRepository(private val api: NewsApi, private val db: AppDatabase) {

    private val defaultSize = 10
    private val country = "us"

    fun fetchEverything(query: String?, page: Int?) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val result = api.fetchEverything(query, Constants.apiKey, defaultSize, page)
            result.articles?.let { db.articlesDao().insertArticles(it) }
            emit(Resource.success(data = result))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun insertNewNews(article: Articles) {
        CoroutineScope(Dispatchers.IO).launch {
            article.isFavorite = true
            db.articlesDao().insertArticle(article)
        }
    }

    fun fetchFavorites() = liveData(Dispatchers.IO) {
        emit(db.articlesDao().fetchFavoriteArticles())
    }

    fun fetchTopHeadlines() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.fetchTopHeadlines("business", country, Constants.apiKey)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}