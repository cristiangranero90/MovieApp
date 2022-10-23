package com.progressapp.movieapp.data

import com.progressapp.movieapp.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface RestDataSource {

    @GET("movie/latest?")
    suspend fun getPopularMovies() : Response<MovieResponse>

}