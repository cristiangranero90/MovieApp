package com.progressapp.movieapp.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.progressapp.movieapp.model.MovieDetailed
import com.progressapp.movieapp.model.MovieResponse
import com.progressapp.movieapp.repositories.MovieRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelMain @Inject constructor(
    private val movieRepo : MovieRepositoryImp
) : ViewModel() {

    private val _resultsPopular = mutableStateListOf<MovieResponse>()
    private val resultsPopular = _resultsPopular
    private val _resultsUpcoming = mutableStateListOf<MovieResponse>()
    private val resultsUpcoming = _resultsUpcoming
    private val _resultsTopRated = mutableStateListOf<MovieResponse>()
    private val resultsTopRated = _resultsTopRated
    private val _resultsNowPlaying = mutableStateListOf<MovieResponse>()
    private val resultsNowPlaying = _resultsNowPlaying
    private val _resultsDiscover = mutableStateListOf<MovieResponse>()
    private val resultsDiscover = _resultsDiscover
    private var movieDetailed: MovieDetailed = getDetail(500)
    private var page = 1
    val isLoading = mutableStateOf(false)

    fun addFavourite(movieDetailed: MovieDetailed)  {
        viewModelScope.launch(Dispatchers.Main) {
            movieRepo.addToFavourites(movieDetailed)
        }
    }

    private fun getPopular()  {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.value = true
            try{
                _resultsPopular
                    .addAll(movieRepo
                    .getPopularMovies(
                        getPage().toString())
                    .movieList)
            }
            catch (e: Exception ) {
                println(e.toString())
            }
            isLoading.value = false
        }
    }

    private fun getMovieDetails(id: Long){
        viewModelScope.launch(Dispatchers.Main) {
            isLoading.value = true
            try {
                movieDetailed = movieRepo.getDetailedMovie(id)
            }
            catch (e: Exception){
                println(e.toString())
            }
            isLoading.value = false || (movieDetailed.id != id.toInt())
        }
    }

    fun getUpcoming() : MutableList<MovieResponse> {
        if(resultsUpcoming.isEmpty()){
            viewModelScope.launch(Dispatchers.Main) {
                isLoading.value = true
                try{
                    _resultsUpcoming
                        .addAll(movieRepo
                            .getUpcoming()
                            .movieList)
                }
                catch (e: Exception ) {
                    println(e.toString())
                }
                isLoading.value = false
            }
        }
        return resultsUpcoming
    }

    fun getTopRated() : MutableList<MovieResponse> {
        if(resultsTopRated.isEmpty()){
            viewModelScope.launch(Dispatchers.Main) {
                isLoading.value = true
                try{
                    _resultsTopRated
                        .addAll(movieRepo
                            .getTopRated()
                            .movieList)
                }
                catch (e: Exception ) {
                    println(e.toString())
                }
                isLoading.value = false
            }
        }
        return resultsTopRated
    }

    fun getNowPlaying() : MutableList<MovieResponse> {
        if(resultsNowPlaying.isEmpty()){
            viewModelScope.launch(Dispatchers.Main) {
                isLoading.value = true
                try{
                    _resultsNowPlaying
                        .addAll(movieRepo
                            .getNowPlaying()
                            .movieList)
                }
                catch (e: Exception ) {
                    println(e.toString())
                }
                isLoading.value = false
            }
        }
        return resultsNowPlaying
    }

    fun getDiscover() : MutableList<MovieResponse> {
        if(resultsDiscover.isEmpty()){
            viewModelScope.launch(Dispatchers.Main) {
                isLoading.value = true
                try{
                    _resultsDiscover
                        .addAll(0, movieRepo
                            .getDiscover()
                            .movieList)
                }
                catch (e: Exception ) {
                    println(e.toString())
                }
                isLoading.value = false
            }
        }
        return resultsDiscover
    }

    private fun getPage() : Int {
        return if (page <= 100){
            page++
        } else {
            page = 1
            page
        }
    }

    fun getDetail(id: Long): MovieDetailed {
        getMovieDetails(id)
        return movieDetailed
    }

    fun getMovieResults(): MutableList<MovieResponse> {
        getPopular()
        return resultsPopular
    }

    fun getResults() : MutableList<MovieResponse>{
        return resultsPopular
    }
}

