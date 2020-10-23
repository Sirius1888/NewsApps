package com.example.newsapp.data.network

import com.example.newsapp.data.model.ResponseBody
import retrofit2.http.*

interface NewsApi {

    @GET("v2/everything")
    suspend fun fetchEverything(
        @Query("q") q: String?,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") size: Int,
        @Query("page") page: Int?
    ): ResponseBody

    @GET("v2/top-headlines")
    suspend fun fetchTopHeadlines(
        @Query("category") category: String,
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): ResponseBody

}