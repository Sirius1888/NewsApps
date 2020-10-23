package com.example.newsapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.model.Articles

@Dao
interface ArticlesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArticles(dto: List<Articles>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(dto: Articles)

    @Query("SELECT * FROM articles")
    suspend fun fetchAllArticles() : List<Articles>

    @Query("SELECT * FROM articles WHERE isFavorite == 1")
    suspend fun fetchFavoriteArticles(): List<Articles>
}