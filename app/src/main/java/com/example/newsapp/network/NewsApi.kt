package com.example.newsapp.network

import com.example.newsapp.model.ResponseBody
import retrofit2.Call
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
    fun fetchTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<ResponseBody>

//    @POST("v2/top-headlines")
//    fun creatEverything(@Body dto: Articles): Call<ResponseBody>

}