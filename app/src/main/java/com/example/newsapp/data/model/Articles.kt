package com.example.newsapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Source(
    var id: String?,
    var name: String?
)

@Entity(tableName = "articles")
data class Articles(
//    var source: Source? = null,
    var author: String,
    @PrimaryKey(autoGenerate = false)
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String,
    var content: String,
    var isFavorite: Boolean = false
)