package com.example.newsapp.ui.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.newsapp.R
import com.example.newsapp.extension.showToast
import com.example.newsapp.model.Articles
import com.example.newsapp.ui.detail_news.DetailNewsActivity
import com.example.newsapp.ui.news.adapter.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopHeadlinesFragment : Fragment(), NewsAdapter.Listener {

    private val viewModel by viewModel<NewsViewModel>()
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_headlines, container, false)
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
//        showToast(R.string.no_more_info)
    }

    private fun updateAdapter(list: MutableList<Articles>?) {
//        list?.let { adapter.update(it) }
    }

    override fun onItemClick(item: Articles) {
        DetailNewsActivity.instanceActivity(activity?.parent, item)
    }

}