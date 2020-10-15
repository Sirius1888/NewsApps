package com.example.newsapp.repository

import android.provider.SyncStateContract
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.network.NewsApi
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.liveData
import com.example.newsapp.Constants
import com.example.newsapp.model.Articles
import com.example.newsapp.model.ResponseBody
import com.example.newsapp.network.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class NewsRepository(val api: NewsApi) {

    private val defaultSize = 10
    fun fetchEverything(query: String?, page: Int?) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.fetchEverything(query, Constants.apiKey, defaultSize, page)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    private val country = "us"
    fun fetchTopHeadlines(): MutableLiveData<MutableList<Articles>?> {
        val data: MutableLiveData<MutableList<Articles>?> = MutableLiveData()
        api.fetchTopHeadlines(country, Constants.apiKey).enqueue(object :
            Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //500.. и выше
                data.value = null
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                //404 - не найдено, 401 - нет доступа, 403 - токен истек
                data.value = response.body()?.articles
            }
        })
        return data
    }

}