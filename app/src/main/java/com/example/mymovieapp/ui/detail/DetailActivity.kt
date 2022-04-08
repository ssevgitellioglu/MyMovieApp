package com.example.mymovieapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.mymovieapp.R
import com.example.mymovieapp.adapter.ViewPagerAdapter
import com.example.mymovieapp.common.BaseActivity
import com.example.mymovieapp.databinding.ActivityDetailBinding
import com.example.mymovieapp.model.movie.MovieResults
import com.example.mymovieapp.ui.detail.overview.OverViewFragment
import com.example.mymovieapp.utils.Constants
import com.example.mymovieapp.viewmodel.DetailViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {

  private var movie: MovieResults? = null

  private var isFav: Boolean? = null

  override fun getLayoutRes(): Int = R.layout.activity_detail

  override fun getViewModel(): Class<DetailViewModel> = DetailViewModel::class.java

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setupUI()
    val detail_tabs: TabLayout = findViewById<View>(R.id.detail_tabs) as TabLayout
    val detail_viewpager: ViewPager = findViewById<View>(R.id.detail_viewpager) as ViewPager
    val favorite_fab: FloatingActionButton = findViewById<View>(R.id.favorite_fab) as FloatingActionButton
    intent.extras.let {
      movie = it?.getParcelable(Constants.EXTRA_MOVIES)
      setupViewPager(movie)
      fabBehaviour(movie)
      detail_tabs.setupWithViewPager(detail_viewpager)

      checkFav()

      favorite_fab.setOnClickListener { favorite() }

      dataBinding.movie = movie
    }
  }

  private fun favorite() {
    if(isFav!!) {
      viewModel.deleteMovie(movie)
      Toast.makeText(this, "Favorilerden çıkarıldı", Toast.LENGTH_SHORT).show()
    } else {
      viewModel.insertMovie(movie)
      Toast.makeText(this, "Favorilere eklendi", Toast.LENGTH_SHORT).show()
    }
  }

  private fun checkFav() {
    val favorite_fab: FloatingActionButton = findViewById<View>(R.id.favorite_fab) as FloatingActionButton
    viewModel.getSingleMovie(movie?.movieId).observe(this, Observer {
      if(it != null) {
        favorite_fab.setImageResource(R.drawable.ic_star_border)
        isFav = true
      } else {
        favorite_fab.setImageResource(R.drawable.ic_star)
        isFav = false
      }
    })
  }

  private fun fabBehaviour(movie: MovieResults?) {
    val favorite_fab: FloatingActionButton = findViewById<View>(R.id.favorite_fab) as FloatingActionButton
    val detail_toolbar: Toolbar = findViewById<View>(R.id.detail_toolbar) as Toolbar
    val detail_appbar_layout: AppBarLayout = findViewById<View>(R.id.detail_appbar_layout) as AppBarLayout
    val detail_collapsing_toolbarlayout: CollapsingToolbarLayout = findViewById<View>(R.id.detail_collapsing_toolbarlayout) as CollapsingToolbarLayout
    detail_appbar_layout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffsett ->
      if(Math.abs(verticalOffsett) - appBarLayout.totalScrollRange == 0) {
        favorite_fab.hide()
        supportActionBar?.setDisplayShowTitleEnabled(true)
        detail_toolbar.title = movie?.movieName
      } else {
        favorite_fab.show()
        supportActionBar?.setDisplayShowTitleEnabled(false)
        detail_toolbar.title = " "
      }
    })

    detail_collapsing_toolbarlayout.setExpandedTitleColor(resources.getColor(android.R.color.transparent))
  }

  private fun setupUI() {
    val detail_toolbar: Toolbar = findViewById<View>(R.id.detail_toolbar) as Toolbar
    setSupportActionBar(detail_toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowTitleEnabled(false)
  }

  private fun setupViewPager(movie: MovieResults?) {
    val adapter = ViewPagerAdapter(supportFragmentManager)
    val detail_viewpager: ViewPager = findViewById<View>(R.id.detail_viewpager) as ViewPager
    adapter.apply {
      addFragment(OverViewFragment.newInstance(movie),"Overview")

    }
    detail_viewpager.adapter = adapter
  }
}
