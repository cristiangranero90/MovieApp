package com.progressapp.movieapp.data

import com.progressapp.movieapp.model.MovieDetailed
import com.progressapp.movieapp.model.MovieList
import com.progressapp.movieapp.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestDataSource {

    @GET("movie/latest?")
    suspend fun getLatestMovie() : Response<MovieResponse>

    @GET("movie/popular?api_key=cb86974c362f47d464bb3b6c94b8f7c2&include_adult=false")
    suspend fun getPopularMovies(@Query ("language") lang: String,
                                 @Query ("page") page: String)
    : Response<MovieList>

    @GET("movie/{movie_id}?api_key=cb86974c362f47d464bb3b6c94b8f7c2")
    suspend fun getMovieDetails(@Path("movie_id") id: Long,
                                @Query("language") lang: String)
    : Response<MovieDetailed>

    @GET("movie/upcoming?api_key=cb86974c362f47d464bb3b6c94b8f7c2&include_adult=false&page=1")
    suspend fun getUpcoming(@Query ("language") lang: String) : Response<MovieList>

    @GET("movie/top_rated?api_key=cb86974c362f47d464bb3b6c94b8f7c2&include_adult=false&page=1")
    suspend fun getTopRated(@Query ("language") lang: String) : Response<MovieList>

    @GET("movie/now_playing?api_key=cb86974c362f47d464bb3b6c94b8f7c2&include_adult=false")
    suspend fun getNowPlaying(@Query ("language") lang: String) : Response<MovieList>

    @GET("discover/movie?api_key=cb86974c362f47d464bb3b6c94b8f7c2&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_watch_monetization_types=flatrate")
    suspend fun getDiscover(@Query ("language") lang: String) : Response<MovieList>

}