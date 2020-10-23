package com.example.newsapp.ui.news.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.newsapp.ui.news.fragments.everything.EverythingFragment
import com.example.newsapp.ui.news.fragments.favorite.FavoriteFragment
import com.example.newsapp.ui.news.fragments.topheadlines.TopHeadlinesFragment

class NewsPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> EverythingFragment()
            1 -> TopHeadlinesFragment()
            2 -> FavoriteFragment()
            else -> EverythingFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

}