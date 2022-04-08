package com.example.mymovieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mymovieapp.model.detail.MovieDetailResponse
import com.example.mymovieapp.repository.OverviewRepository

class OverviewViewModel: ViewModel() {

  private val repository: OverviewRepository by lazy { OverviewRepository() }

  fun getDetails(movieId: Int): LiveData<MovieDetailResponse> = repository.getDetails(movieId)

}
