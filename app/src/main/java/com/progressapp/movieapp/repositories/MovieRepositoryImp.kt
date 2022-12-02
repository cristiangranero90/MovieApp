package com.progressapp.movieapp.repositories

import com.progressapp.movieapp.dao.MovieDao
import com.progressapp.movieapp.data.RestDataSource
import com.progressapp.movieapp.model.Movie
import com.progressapp.movieapp.model.MovieList
import com.progressapp.movieapp.model.MovieResponse
import com.progressapp.movieapp.model.MovieDetailed
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource,
    private val movieDao : MovieDao
) : MovieRepository {

    private val lang: String = Locale.getDefault().language + "-" + Locale.getDefault().country

    override suspend fun getLatestMovie(): MovieResponse? {

        val response : Response<MovieResponse> = dataSource.getLatestMovie()

        if (response.isSuccessful)
            return response.body()

        else{
            throw RuntimeException("No movies found")
        }
    }

    override suspend fun getPopularMovies(page: String): MovieList {

        val response : Response<MovieList> = dataSource.getPopularMovies(lang, page)

        if (response.isSuccessful){
            return response.body()!!
        }
        else{
            throw RuntimeException("Movie list empty, an error has occurred")
        }
    }

    override suspend fun getDetailedMovie(id: Long) : MovieDetailed {

        val response: Response<MovieDetailed> = dataSource.getMovieDetails(id, lang)

        if (response.isSuccessful){
            return response.body()!!
        }
        else{
            throw RuntimeException("Movie details, it cannot be fetched")
        }
    }

    override suspend fun getUpcoming(): MovieList {

        val response : Response<MovieList> = dataSource.getUpcoming(lang)

        if (response.isSuccessful){
            return response.body()!!
        }
        else{
            throw RuntimeException("Movie list empty, an error has occurred")
        }
    }

    override suspend fun getTopRated(): MovieList {

        val response : Response<MovieList> = dataSource.getTopRated(lang)

        if (response.isSuccessful){
            return response.body()!!
        }
        else{
            throw RuntimeException("Movie list empty, an error has occurred")
        }
    }

    override suspend fun getNowPlaying(): MovieList {

        val response : Response<MovieList> = dataSource.getNowPlaying(lang)

        if (response.isSuccessful){
            return response.body()!!
        }
        else{
            throw RuntimeException("Movie list empty, an error has occurred")
        }
    }

    override suspend fun getDiscover(): MovieList {

        val response : Response<MovieList> = dataSource.getDiscover(lang)

        if (response.isSuccessful){
            return response.body()!!
        }
        else{
            throw RuntimeException("Movie list empty, an error has occurred")
        }
    }

    //Database methods
    override suspend fun addToFavourites(movie: MovieDetailed): Long {
        return movieDao
            .addMovie(Movie(title = movie.title,
                poster_path = movie.poster_path,
                idtmdb = movie.id))
    }

    override suspend fun deleteMovie(toDelete: Movie) {
        movieDao.deleteMovie(toDelete)
    }

    override suspend fun updateMovie(toUpdate: Movie) {
        movieDao.updateMovie(toUpdate)
    }

    override suspend fun getAllMovies(): MutableList<Movie> {
        return movieDao.getAllMovies()
    }


}