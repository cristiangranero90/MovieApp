package com.progressapp.movieapp.repositories

import com.progressapp.movieapp.data.RestDataSource
import com.progressapp.movieapp.model.MovieList
import com.progressapp.movieapp.model.MovieResponse
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource
) : MovieRepository {

    override suspend fun getLatestMovie(): MovieResponse? {
        val response : Response<MovieResponse> = dataSource.getLatestMovie()

        if (response.isSuccessful)
            return response.body()

        return null
    }

    override suspend fun getPopularMovies(): MovieList {

        val response : Response<MovieList> = dataSource.getPopularMovies()

        if (response.isSuccessful){
            return response.body()!!
        }
        else{
            throw RuntimeException("Movie list empty, an error occured")
        }
    }


}