package com.progressapp.movieapp.data.repository

import com.progressapp.movieapp.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiRepository {

    @GET
    suspend fun getPopularMovies(@Url url : String) : Response<MovieResponse>

}