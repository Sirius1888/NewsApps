package com.example.newsapp.ui.news.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.newsapp.ui.news.EverythingFragment
import com.example.newsapp.ui.news.TopHeadlinesFragment

class NewsPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> EverythingFragment()
            1 -> TopHeadlinesFragment()
            else -> EverythingFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

}