package com.example.newsapp.model

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
) {
    fun sortArticlesArray(list: MutableList<Articles>?): MutableList<Articles>? {
        list?.filter { it.urlToImage != null
                && it.title != null}
        return list
    }

    fun isEmptyImage(): String {
        return when (urlToImage.isNullOrEmpty()) {
            true -> "ДА, изображение пустое"
            false -> "нет, тут есть изображение"
        }
    }

}

