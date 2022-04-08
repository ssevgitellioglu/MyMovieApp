package com.example.mymovieapp.data.remote

import com.example.mymovieapp.model.detail.MovieDetailResponse
import com.example.mymovieapp.model.movie.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
  //Popular
  @GET("tv/popular")
  fun getPopularMovies(): Call<MovieResponse>
  //Details
  @GET("tv/{id}")
  fun getMoviesDetail(@Path("id") movieId :Int): Call<MovieDetailResponse>

}