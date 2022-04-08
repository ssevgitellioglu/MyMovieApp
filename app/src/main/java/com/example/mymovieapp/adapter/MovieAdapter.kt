package com.example.mymovieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.databinding.ItemMovieBinding
import com.example.mymovieapp.model.movie.MovieResults

class MovieAdapter: ListAdapter<MovieResults, MovieAdapter.ViewHolder>(DIFF_CALLBACK) {

  private lateinit var onMovieClickListener: OnMovieClickListener

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
    ViewHolder.create(LayoutInflater.from(parent.context), parent, onMovieClickListener)

  override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

  fun setOnMovieClickListener(onMovieClickListener: OnMovieClickListener) {
    this.onMovieClickListener = onMovieClickListener
  }

  class ViewHolder(val binding: ItemMovieBinding, onMovieClickListener: OnMovieClickListener): RecyclerView.ViewHolder(binding.root) {

    init {
      binding.root.setOnClickListener {
        onMovieClickListener.onMovieClick(binding.movie!!)
      }
    }

    companion object {
      fun create(inflater: LayoutInflater, parent: ViewGroup, onMovieClickListener: OnMovieClickListener): ViewHolder {
        val itemMovieBinding = ItemMovieBinding.inflate(inflater, parent, false)
        return ViewHolder(itemMovieBinding, onMovieClickListener)
      }
    }

    fun bind(movieResults: MovieResults) {
      binding.movie = movieResults
      binding.executePendingBindings()
    }
  }

  companion object {
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieResults>() {
      override fun areItemsTheSame(oldItem: MovieResults, newItem: MovieResults): Boolean =
        oldItem.movieId == newItem.movieId

      override fun areContentsTheSame(oldItem: MovieResults, newItem: MovieResults): Boolean =
        oldItem.movieName == newItem.movieName

    }
  }

  interface OnMovieClickListener {
    fun onMovieClick(movieResults: MovieResults)
  }
}
