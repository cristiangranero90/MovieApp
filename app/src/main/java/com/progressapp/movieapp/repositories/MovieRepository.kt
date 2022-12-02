package com.progressapp.movieapp.repositories

import com.progressapp.movieapp.model.Movie
import com.progressapp.movieapp.model.MovieDetailed
import com.progressapp.movieapp.model.MovieList
import com.progressapp.movieapp.model.MovieResponse

interface MovieRepository {

    suspend fun getLatestMovie() : MovieResponse?

    suspend fun getPopularMovies(page: String) : MovieList?

    suspend fun getDetailedMovie(id: Long) : MovieDetailed

    suspend fun getUpcoming() : MovieList

    suspend fun getTopRated() : MovieList

    suspend fun getNowPlaying() : MovieList

    suspend fun getDiscover() : MovieList

    suspend fun addToFavourites(movie: MovieDetailed) : Long

    suspend fun deleteMovie(toDelete: Movie)

    suspend fun updateMovie(toUpdate: Movie)

    suspend fun getAllMovies() : MutableList<Movie>

}