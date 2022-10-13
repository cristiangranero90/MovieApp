package com.progressapp.movieapp.services

import com.progressapp.movieapp.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getPopularMovies(@Url url : String) : Response<MovieResponse>

}