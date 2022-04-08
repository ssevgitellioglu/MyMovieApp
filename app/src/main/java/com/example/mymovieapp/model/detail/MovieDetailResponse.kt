package com.example.mymovieapp.model.detail

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
  @PrimaryKey @SerializedName("id") val id : Int?,
  @SerializedName("name") val name : String?,
  @SerializedName("backdrop_path") val backdrop_path : String?,
  @SerializedName("overview") val overview : String?,
  @SerializedName("poster_path") val poster_path : String?,
  @SerializedName("first_air_date") val first_air_date : String?,
  @SerializedName("original_name") val original_name : String?,
  @SerializedName("title") val title : String?,
  @SerializedName("status") val status : String?,
  @SerializedName("type") val type : String?,
  @SerializedName("vote_average") val vote_average : Double?,
  @SerializedName("vote_count") val vote_count : Int?,
  @SerializedName("seasons") val seasons : MovieDetailSeasons,
)
