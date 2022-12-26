package com.progressapp.movieapp.data

import com.progressapp.movieapp.BuildConfig
import com.progressapp.movieapp.model.MovieDetailed
import com.progressapp.movieapp.model.MovieList
import com.progressapp.movieapp.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestDataSource {

    @GET("movie/latest?${BuildConfig.API_KEY}")
    suspend fun getLatestMovie() : Response<MovieResponse>

    @GET("movie/popular?${BuildConfig.API_KEY}&include_adult=false")
    suspend fun getPopularMovies(@Query ("language") lang: String,
                                 @Query ("page") page: String)
    : Response<MovieList>

    @GET("movie/{movie_id}?${BuildConfig.API_KEY}")
    suspend fun getMovieDetails(@Path("movie_id") id: Long,
                                @Query("language") lang: String)
    : Response<MovieDetailed>

    @GET("movie/upcoming?${BuildConfig.API_KEY}&include_adult=false&page=1")
    suspend fun getUpcoming(@Query ("language") lang: String) : Response<MovieList>

    @GET("movie/top_rated?${BuildConfig.API_KEY}&include_adult=false&page=1")
    suspend fun getTopRated(@Query ("language") lang: String) : Response<MovieList>

    @GET("movie/now_playing?${BuildConfig.API_KEY}&include_adult=false")
    suspend fun getNowPlaying(@Query ("language") lang: String) : Response<MovieList>

    @GET("discover/movie?${BuildConfig.API_KEY}&sort_by=popularity.desc&include_adult=false&include_video=false&with_watch_monetization_types=flatrate")
    suspend fun getDiscover(@Query ("language") lang: String) : Response<MovieList>

    @GET("search/movie?${BuildConfig.API_KEY}&page=1&include_adult=false")
    suspend fun searchMovie(@Query ("language") lang: String, @Query ("query") toSearch: String) : Response<MovieList>

}