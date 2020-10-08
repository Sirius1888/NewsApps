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
import com.example.newsapp.ui.detail_news.DetailNewsActivity
import com.example.newsapp.ui.news.adapter.NewsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsActivity : AppCompatActivity(), NewsAdapter.Listener {

    private val viewModel by viewModel<NewsViewModel>()
    private lateinit var adapter: NewsAdapter
    private val linearManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        subscribeToEverything()
    }

    private fun setupViews() {
        setupRecyclerView()
        setupScrollListener()
    }

    private fun subscribeToEverything() {
        viewModel.fetchEverything("bitcoin").observe(this, Observer {
            when (it.isNullOrEmpty()) {
                false -> updateAdapter(it)
            }
            viewModel.isLoading = false
        })
    }

    private fun subscribeToTopHeadlines() {
        viewModel.fetchTopHeadlines().observe(this, Observer {
            when (it != null) {
                true -> setLastPage()
                false -> updateAdapter(it)
            }
            viewModel.isLoading = false
        })
    }

    private fun setLastPage() {
        viewModel.isLastPage = true
        showToast(R.string.no_more_info)
    }

    private fun setupRecyclerView() {
        adapter = NewsAdapter(this)
        recycler_view.layoutManager = linearManager
        recycler_view.adapter = adapter
    }

    private fun setupScrollListener() {
        recycler_view?.addOnScrollListener(object : PaginationScrollListener(linearManager) {
            override fun isLastPage(): Boolean {
                return viewModel.isLastPage
            }

            override fun isLoading(): Boolean {
                return viewModel.isLoading
            }

            override fun loadMoreItems() {
                viewModel.isLoading = true
                subscribeToEverything()
            }
        })
    }

    private fun updateAdapter(list: MutableList<Articles>?) {
        list?.let { adapter.update(it) }
    }

    override fun onItemClick(item: Articles) {
        DetailNewsActivity.instanceActivity(this, item)
    }

}