package com.example.mymovieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mymovieapp.model.movie.MovieResults
import com.example.mymovieapp.repository.MainRepository

class PopularMoviesViewModel: ViewModel() {
  private val repository: MainRepository = MainRepository()
  fun getPopularMovies(): LiveData<List<MovieResults>>?=repository.getPopularMovies()
}