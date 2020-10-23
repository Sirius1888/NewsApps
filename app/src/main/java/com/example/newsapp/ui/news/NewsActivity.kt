package com.example.newsapp.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapp.R
import com.example.newsapp.ui.news.adapter.NewsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

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
        view_pager.offscreenPageLimit = 3
    }

    private fun setupBottomNavigationView() {
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.everything -> changeCurrentPosition(0)
                R.id.top_headlines -> changeCurrentPosition(1)
                R.id.favorites -> changeCurrentPosition(2)
            }
            true
        }
    }

    private fun changeCurrentPosition(position: Int) {
        view_pager.currentItem = position
    }

}