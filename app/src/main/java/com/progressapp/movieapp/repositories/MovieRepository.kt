package com.progressapp.movieapp.repositories

import com.progressapp.movieapp.model.MovieResponse
import retrofit2.Response

interface MovieRepository {

    suspend fun getLatestMovie() : Response<MovieResponse>

}