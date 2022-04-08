package com.example.mymovieapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mymovieapp.model.movie.MovieResults


@Dao
interface MovieDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMovie(movie: MovieResults?)

  @Delete
  fun deleteMovie(movie: MovieResults?)

  @Query("SELECT * FROM movies")
  fun getAllMovies(): LiveData<List<MovieResults>>

  @Query("SELECT * FROM movies WHERE movieId= :movieId")
  fun getSingleMovie(movieId: Int?): LiveData<MovieResults>
}
