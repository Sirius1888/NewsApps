package com.example.newsapp.data.model

data class ResponseBody(
    var status: String? = null,
    var totalResults: Int? = null,
    var articles: MutableList<Articles>? = null,
    var message: String? = null,
    var code: String? = null
)