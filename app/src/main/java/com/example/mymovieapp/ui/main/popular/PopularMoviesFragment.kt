package com.example.mymovieapp.ui.main.popular

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.R
import com.example.mymovieapp.adapter.MovieAdapter
import com.example.mymovieapp.common.BaseVMFragment
import com.example.mymovieapp.model.movie.MovieResults
import com.example.mymovieapp.ui.detail.DetailActivity
import com.example.mymovieapp.utils.Constants
import com.example.mymovieapp.utils.gone
import com.example.mymovieapp.utils.visible
import com.example.mymovieapp.viewmodel.PopularMoviesViewModel


class PopularMoviesFragment: BaseVMFragment<PopularMoviesViewModel>(), MovieAdapter.OnMovieClickListener {

  private lateinit var adapter: MovieAdapter

  override fun getViewModel(): Class<PopularMoviesViewModel> = PopularMoviesViewModel::class.java

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_popular_movies, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    val popular_recyclerview: RecyclerView = view.findViewById<View>(R.id.popular_recyclerview) as RecyclerView
    val popular_progressbar: ProgressBar = view.findViewById<View>(R.id.popular_progressbar) as ProgressBar
    adapter = MovieAdapter()
    popular_recyclerview.layoutManager = GridLayoutManager(activity, 2)

    adapter.setOnMovieClickListener(this)

    viewModel.getPopularMovies()?.observe(viewLifecycleOwner, Observer {
      adapter.submitList(it)
      popular_recyclerview.adapter = adapter
      popular_recyclerview.visible()
      popular_progressbar.gone()
    })
  }

  override fun onMovieClick(movieResults: MovieResults) {
    val intent = Intent(activity, DetailActivity::class.java)
    intent.putExtra(Constants.EXTRA_MOVIES, movieResults)
    startActivity(intent)
  }
}

