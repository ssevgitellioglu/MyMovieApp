package com.example.mymovieapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovieapp.data.remote.ApiClient
import com.example.mymovieapp.data.remote.ApiService
import com.example.mymovieapp.model.movie.MovieResponse
import com.example.mymovieapp.model.movie.MovieResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {
  private val apiService: ApiService by lazy { ApiClient.getApiService() }

  fun getPopularMovies(): LiveData<List<MovieResults>>? {
    val moviesLiveData: MutableLiveData<List<MovieResults>> = MutableLiveData()
    apiService.getPopularMovies().enqueue(object : Callback<MovieResponse> {
      override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
        t.message?.let { Log.e("getPopularMovies", it) }
      }

      override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
        moviesLiveData.value = response.body()?.results
      }

    })

    return moviesLiveData
  }
}