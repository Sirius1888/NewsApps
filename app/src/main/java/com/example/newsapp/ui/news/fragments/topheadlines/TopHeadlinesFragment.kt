package com.example.newsapp.ui.news.fragments.topheadlines

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.extension.showToast
import com.example.newsapp.data.model.Articles
import com.example.newsapp.data.network.Status
import com.example.newsapp.helper.PaginationScrollListener
import com.example.newsapp.ui.detail_news.DetailNewsActivity
import com.example.newsapp.ui.news.adapter.NewsAdapter
import kotlinx.android.synthetic.main.fragment_everything.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopHeadlinesFragment : Fragment(), NewsAdapter.Listener {

    private val viewModel by viewModel<TopHeadlinesViewModel>()
    private lateinit var adapter: NewsAdapter
    private val linearManager = LinearLayoutManager(activity?.parent)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_headlines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupScrollListener()
        subscribeToTopHeadlines()
    }

    private fun setupRecyclerView() {
        adapter = NewsAdapter(this)
        recycler_view.layoutManager = linearManager
        recycler_view.adapter = adapter
    }

    private fun subscribeToTopHeadlines() {
        viewModel.fetchTopHeadlines().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    when (it != null) {
                        true -> setLastPage()
                        false -> updateAdapter(it)
                    }
                    viewModel.isLoading = false
                }
            }
        })
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
                subscribeToTopHeadlines()
            }
        })
    }

    private fun setLastPage() {
        viewModel.isLastPage = true
        activity?.showToast(R.string.no_more_info)
    }

    private fun updateAdapter(list: MutableList<Articles>?) {
        list?.let { adapter.update(it) }
    }

    override fun onItemClick(item: Articles) {
        DetailNewsActivity.instanceActivity(activity?.parent, item)
    }
}