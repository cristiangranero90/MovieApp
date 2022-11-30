package com.progressapp.movieapp.repositories

import com.progressapp.movieapp.model.MovieDetailed
import com.progressapp.movieapp.model.MovieList
import com.progressapp.movieapp.model.MovieResponse
import retrofit2.Response

interface MovieRepository {

    suspend fun getLatestMovie() : MovieResponse?

    suspend fun getPopularMovies(page: String) : MovieList?

    suspend fun getDetailedMovie(id: Long) : MovieDetailed

    suspend fun getUpcoming() : MovieList

    suspend fun getTopRated() : MovieList

    suspend fun getNowPlaying() : MovieList

    suspend fun getDiscover() : MovieList

}