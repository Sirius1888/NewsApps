package com.example.newsapp.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.extension.showToast
import com.example.newsapp.helper.PaginationScrollListener
import com.example.newsapp.model.Articles
import com.example.newsapp.network.Status
import com.example.newsapp.ui.detail_news.DetailNewsActivity
import com.example.newsapp.ui.news.adapter.NewsAdapter
import com.example.newsapp.ui.news.adapter.NewsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsActivity : AppCompatActivity() {

    private lateinit var viewPager: NewsPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    private fun setupViews() {
        setupViewPager()
        setupBottomNavigationView()
    }

    private fun setupViewPager() {
        viewPager = NewsPagerAdapter(supportFragmentManager)
        view_pager.adapter = viewPager
        view_pager.offscreenPageLimit = 2
    }

    private fun setupBottomNavigationView() {
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.everything -> changeCurrentPosition(0)
                R.id.top_headlines -> changeCurrentPosition(1)
            }
            true
        }
    }

    private fun changeCurrentPosition(position: Int) {
        view_pager.currentItem = position
    }

}