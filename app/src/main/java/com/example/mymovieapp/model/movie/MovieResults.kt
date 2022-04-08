package com.example.mymovieapp.model.movie

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieResults (

  @PrimaryKey@SerializedName("id") val movieId : Int,
  @SerializedName("poster_path") val poster_path : String?,
  @SerializedName("overview") val overview : String?,
  @SerializedName("first_air_date") val first_air_date : String?,
  @SerializedName("original_language") val original_language : String?,
  @SerializedName("original_name") val original_name : String?,
  @SerializedName("name") val movieName : String?,
  @SerializedName("backdrop_path") val backdrop_path : String?,
  @SerializedName("popularity") val popularity : Double,
  @SerializedName("vote_average") val vote_average : Double,
  @SerializedName("vote_count") val vote_count : Int
  ) :Parcelable {
  constructor(parcel: Parcel) : this(
    parcel.readInt(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readDouble(),
    parcel.readDouble(),
    parcel.readInt()
  ) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeInt(movieId)
    parcel.writeString(poster_path)
    parcel.writeString(overview)
    parcel.writeString(first_air_date)
    parcel.writeString(original_language)
    parcel.writeString(original_name)
    parcel.writeString(movieName)
    parcel.writeString(backdrop_path)
    parcel.writeDouble(popularity)
    parcel.writeDouble(vote_average)
    parcel.writeInt(vote_count)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<MovieResults> {
    override fun createFromParcel(parcel: Parcel): MovieResults {
      return MovieResults(parcel)
    }

    override fun newArray(size: Int): Array<MovieResults?> {
      return arrayOfNulls(size)
    }
  }
}