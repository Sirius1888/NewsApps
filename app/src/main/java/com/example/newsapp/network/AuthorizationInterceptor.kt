package com.example.newsapp.network

import com.example.newsapp.AppNews
import com.example.newsapp.helper.SharedPreference
import okhttp3.Interceptor
import okhttp3.Response


class AuthorizationInterceptor(val prefs: SharedPreference) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request().newBuilder()
            .addHeader("Authorization", prefs.authorized.toString())
            .build()
        return chain.proceed(req)
    }
}