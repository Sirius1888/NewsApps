package com.example.newsapp.ui.news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.extension.loadImage
import com.example.newsapp.data.model.Articles
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_news.*

class NewsAdapter(private val onNewsClickListener: Listener):
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var list = mutableListOf<Articles>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onNewsClickListener.onItemClick(list[position])
        }
    }

    fun update(list: MutableList<Articles>) {
        val size = this.list.size
        this.list.addAll(list)
        val sizeNew = this.list.size
        notifyItemRangeChanged(size, sizeNew)
    }

    override fun getItemCount(): Int = list.size

    class NewsViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: Articles) {
            title_text.text = item.title
            description_text.text = item.description
            image.loadImage(context = image.context, url = item.urlToImage)
        }
    }

    interface Listener {
        fun onItemClick(item: Articles)
    }
}