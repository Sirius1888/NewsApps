package com.example.newsapp.ui.news.fragments.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.extension.gone
import com.example.newsapp.data.model.Articles
import com.example.newsapp.ui.detail_news.DetailNewsActivity
import com.example.newsapp.ui.news.adapter.NewsAdapter
import kotlinx.android.synthetic.main.fragment_everything.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment(), NewsAdapter.Listener {

    private val viewModel by viewModel<FavoriteViewModel>()
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
        subscribeToFetchAllFavorites()
    }

    private fun setupRecyclerView() {
        editQuery.gone()
        adapter = NewsAdapter(this)
        recycler_view.layoutManager = linearManager
        recycler_view.adapter = adapter
    }

    private fun subscribeToFetchAllFavorites() {
        viewModel.fetchAllFavorites().observe(viewLifecycleOwner, Observer {
            updateAdapter(it as MutableList<Articles>?)
        })
    }

    private fun updateAdapter(list: MutableList<Articles>?) {
        list?.let { adapter.update(it) }
    }

    override fun onItemClick(item: Articles) {
        DetailNewsActivity.instanceActivity(activity, item)
    }
}