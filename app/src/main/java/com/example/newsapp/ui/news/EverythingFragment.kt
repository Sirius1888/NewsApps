package com.example.newsapp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.helper.PaginationScrollListener
import com.example.newsapp.model.Articles
import com.example.newsapp.network.Status
import com.example.newsapp.ui.detail_news.DetailNewsActivity
import com.example.newsapp.ui.news.adapter.NewsAdapter
import kotlinx.android.synthetic.main.fragment_everything.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EverythingFragment : Fragment(), NewsAdapter.Listener {

    private val viewModel by viewModel<NewsViewModel>()
    private lateinit var adapter: NewsAdapter
    private val linearManager = LinearLayoutManager(activity?.parent)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_everything, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupScrollListener()
        subscribeToEverything()
    }

    private fun subscribeToEverything() {
        viewModel.fetchEverything("bitcoin").observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    updateAdapter(it.data?.articles)
                }
            }
        })
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
        DetailNewsActivity.instanceActivity(activity, item)
    }

}