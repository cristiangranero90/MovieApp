package com.progressapp.movieapp.data

import com.progressapp.movieapp.model.MovieList
import com.progressapp.movieapp.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface RestDataSource {

    @GET("movie/latest?")
    suspend fun getLatestMovie() : Response<MovieResponse>

    @GET("movie/popular?api_key=cb86974c362f47d464bb3b6c94b8f7c2&language=en-US&page=1")
    suspend fun getPopularMovies() : Response<MovieList>

}