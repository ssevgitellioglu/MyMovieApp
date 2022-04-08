package com.example.mymovieapp.model.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(
  @SerializedName("results")
  var results:List<MovieResults>,
  @SerializedName("page")
  var page:Int,
  @SerializedName("total_pages")
  var total_pages:Int
)