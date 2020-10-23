package com.example.newsapp.ui.detail_news

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapp.R
import com.example.newsapp.extension.loadImage
import com.example.newsapp.data.model.Articles
import com.example.newsapp.ui.news.NewsViewModel
import kotlinx.android.synthetic.main.activity_detail_news.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailNewsActivity : AppCompatActivity() {

    private val viewModel by viewModel<DetailNewsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)
        setupViews()
        addToFavoriteAction()
    }

    private fun setupViews() {
        imageNews.loadImage(this, item?.urlToImage)
        tvAuthor.text = item?.author
        tvTittle.text = item?.title
        tvDesc.text = item?.description
    }

    private fun addToFavoriteAction() {
        add_to_favorite.setOnClickListener {
            item?.isFavorite = true
            item?.let { viewModel.insertEverything(it) }
        }
    }

    companion object {
        private var item: Articles? = null
        fun instanceActivity(activity: Activity?, item: Articles) {
            this.item = item
            val intent = Intent(activity, DetailNewsActivity::class.java)
            activity?.startActivity(intent)
        }
    }
}