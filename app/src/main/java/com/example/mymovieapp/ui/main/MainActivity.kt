package com.example.mymovieapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.mymovieapp.R
import com.example.mymovieapp.adapter.ViewPagerAdapter
import com.example.mymovieapp.ui.favorites.FavoritesFragment
import com.example.mymovieapp.ui.main.popular.PopularMoviesFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setupUI()
  }

  private fun setupUI() {
    val main_toolbar: Toolbar = findViewById<Toolbar>(R.id.main_toolbar)
    val main_viewpager: ViewPager = findViewById<ViewPager>(R.id.main_viewpager)
    val main_tabs: TabLayout = findViewById<TabLayout>(R.id.main_tabs)
    setSupportActionBar(main_toolbar)
    setupViewPager()
    main_tabs.setupWithViewPager(main_viewpager)
  }

  private fun setupViewPager() {
    val adapter = ViewPagerAdapter(supportFragmentManager)
    val main_viewpager: ViewPager = findViewById<ViewPager>(R.id.main_viewpager)
    adapter.apply {
      addFragment(PopularMoviesFragment(), "Popular")
      addFragment(FavoritesFragment(), "Favorites")
    }
    main_viewpager.adapter = adapter
    main_viewpager.offscreenPageLimit = 3
  }
}
