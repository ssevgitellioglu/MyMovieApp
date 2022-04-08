package com.example.mymovieapp.ui.favorites

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
import com.example.mymovieapp.viewmodel.DetailViewModel

class FavoritesFragment: BaseVMFragment<DetailViewModel>(), MovieAdapter.OnMovieClickListener {

  private lateinit var adapter: MovieAdapter

  override fun getViewModel(): Class<DetailViewModel> = DetailViewModel::class.java

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_favorites, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    val favorites_recyclerview: RecyclerView = view.findViewById<View>(R.id.favorites_recyclerview) as RecyclerView
    val favorites_progressbar: ProgressBar = view.findViewById<View>(R.id.favorites_progressbar) as ProgressBar
    adapter = MovieAdapter()
    favorites_recyclerview.layoutManager = GridLayoutManager(activity, 2)

    adapter.setOnMovieClickListener(this)

    viewModel.getAllMovies().observe(viewLifecycleOwner, Observer {
      adapter.submitList(it)
      favorites_recyclerview.adapter = adapter
      favorites_recyclerview.visible()
      favorites_progressbar.gone()
    })
  }

  override fun onMovieClick(movieResults: MovieResults) {
    val intent = Intent(activity, DetailActivity::class.java)
    intent.putExtra(Constants.EXTRA_MOVIES, movieResults)
    startActivity(intent)
  }
}
