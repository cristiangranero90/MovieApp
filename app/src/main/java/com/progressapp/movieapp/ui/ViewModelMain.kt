package com.progressapp.movieapp.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    val isLoading = mutableStateOf(false)

    private fun getPopular()  {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try{
                _resultsPopular
                    .addAll(movieRepo
                    .getPopularMovies(
                        "1")
                    .movieList)
            }
            catch (e: Exception ) {
                println(e.toString())
            }
            isLoading.value = false
        }
    }

    fun getUpcoming() : MutableList<MovieResponse> {
        if(resultsUpcoming.isEmpty()){
            isLoading.value = true
            viewModelScope.launch(Dispatchers.Main) {
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
            isLoading.value = true
            viewModelScope.launch(Dispatchers.Main) {
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
            isLoading.value = true
            viewModelScope.launch(Dispatchers.Main) {
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
            isLoading.value = true
            viewModelScope.launch(Dispatchers.Main) {
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

    fun getMovieResults(): MutableList<MovieResponse> {
        getPopular()
        return resultsPopular
    }
}

