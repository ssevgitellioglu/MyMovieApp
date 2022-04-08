package com.example.mymovieapp.model.detail

import com.google.gson.annotations.SerializedName

data class MovieDetailSeasons(
  @SerializedName("id") val id : Int?,
  @SerializedName("name") val name : String?,
  @SerializedName("episode_count") val episode_count : Int?,
  @SerializedName("season_number") val season_number : Int?,
  @SerializedName("air_date") val air_date : String?,
)
