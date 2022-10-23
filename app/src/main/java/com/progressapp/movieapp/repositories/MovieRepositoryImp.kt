package com.progressapp.movieapp.repositories

import com.progressapp.movieapp.data.RestDataSource
import com.progressapp.movieapp.model.MovieResponse
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource
) : MovieRepository {

    override suspend fun getLatestMovie(): Response<MovieResponse> {
        return dataSource.getPopularMovies()
    }


}