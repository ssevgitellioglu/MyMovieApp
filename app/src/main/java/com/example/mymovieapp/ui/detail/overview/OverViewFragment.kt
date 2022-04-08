package com.example.mymovieapp.ui.detail.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.mymovieapp.R
import com.example.mymovieapp.common.BaseFragment
import com.example.mymovieapp.databinding.FragmentOverViewBinding
import com.example.mymovieapp.model.movie.MovieResults
import com.example.mymovieapp.viewmodel.OverviewViewModel

class OverViewFragment : BaseFragment<FragmentOverViewBinding, OverviewViewModel>() {


  override fun getLayoutRes(): Int = R.layout.fragment_over_view

  override fun getViewModel(): Class<OverviewViewModel> = OverviewViewModel::class.java

  companion object {
    private const val MOVIE_KEY = "movie_overview_key"
    fun newInstance(movie: MovieResults?): OverViewFragment {
      val args = Bundle()
      args.putParcelable(MOVIE_KEY, movie)

      val fragment = OverViewFragment()
      fragment.arguments = args
      return fragment
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)
    val movie: MovieResults? = arguments?.getParcelable(MOVIE_KEY) as MovieResults?
    val movieId = movie?.movieId

    if (movieId != null) {
      viewModel.getDetails(movieId).observe(viewLifecycleOwner, Observer {
        dataBinding.details = it
      })
    }
    return dataBinding.root
  }

}
