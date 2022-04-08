package com.example.mymovieapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mymovieapp.data.remote.ApiClient
import com.example.mymovieapp.data.remote.ApiService
import com.example.mymovieapp.model.detail.MovieDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewRepository {

  private val apiService: ApiService by lazy { ApiClient.getApiService() }

  fun getDetails(movieId: Int): MutableLiveData<MovieDetailResponse> {
    val movieDetailLive: MutableLiveData<MovieDetailResponse> = MutableLiveData()
    apiService.getMoviesDetail(movieId).enqueue(object : Callback<MovieDetailResponse> {
      override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
        t.message?.let { Log.e("getDetails", it) }
      }

      override fun onResponse(
        call: Call<MovieDetailResponse>,
        response: Response<MovieDetailResponse>
      ) {
        movieDetailLive.value = response.body()
      }

    })

    return movieDetailLive
  }
}
