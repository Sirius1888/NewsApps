package com.example.newsapp.model

data class Source(
    var id: String?,
    var name: String?
)

data class Articles(
    var source: Source? = null,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null,
    var content: String? = null
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

