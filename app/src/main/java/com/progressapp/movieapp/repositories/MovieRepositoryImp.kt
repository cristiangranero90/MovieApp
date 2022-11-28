package com.progressapp.movieapp.repositories

import android.graphics.Region
import com.progressapp.movieapp.data.RestDataSource
import com.progressapp.movieapp.model.MovieList
import com.progressapp.movieapp.model.MovieResponse
import com.progressapp.movieapp.model.MovieDetailed
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource
) : MovieRepository {

    override suspend fun getLatestMovie(): MovieResponse? {
        val response : Response<MovieResponse> = dataSource.getLatestMovie()

        if (response.isSuccessful)
            return response.body()

        else{
            throw RuntimeException("No movies found")
        }
    }

    override suspend fun getPopularMovies(lang: String, page: String): MovieList {

        val response : Response<MovieList> = dataSource.getPopularMovies(lang, page)

        if (response.isSuccessful){
            return response.body()!!
        }
        else{
            throw RuntimeException("Movie list empty, an error occured")
        }
    }

    override suspend fun getDetailedMovie(id: Long, lang: String) : MovieDetailed {
        val response: Response<MovieDetailed> = dataSource.getMovieDetails(id, lang)

        if (response.isSuccessful){
            return response.body()!!
        }
        else{
            throw RuntimeException("Movie details, it cannot be fetched")
        }
    }


}