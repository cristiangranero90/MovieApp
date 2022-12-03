package com.progressapp.movieapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.progressapp.movieapp.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie")
    suspend fun getAllMovies() : MutableList<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(toAdd: Movie)

    @Update
    suspend fun updateMovie(toUpdate: Movie)

    @Delete
    suspend fun deleteMovie(toDelete: Movie)

}