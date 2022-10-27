package com.progressapp.movieapp.repositories

import com.progressapp.movieapp.model.MovieList
import com.progressapp.movieapp.model.MovieResponse
import retrofit2.Response

interface MovieRepository {

    suspend fun getLatestMovie() : MovieResponse?

    suspend fun getPopularMovies() : MovieList?

}