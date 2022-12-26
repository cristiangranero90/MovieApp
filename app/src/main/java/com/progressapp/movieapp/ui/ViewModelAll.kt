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
class ViewModelAll @Inject constructor(
    private val movieRepo : MovieRepositoryImp
) : ViewModel() {

    private val _resultsPopular = mutableStateListOf<MovieResponse>()
    private val resultsPopular = _resultsPopular
    private var page = 1
    private val _isLoading = mutableStateOf(false)

    fun isLoading() = _isLoading.value

    private fun getPopular()  {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
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
            _isLoading.value = false
        }
    }

    private fun getPage() : Int {
        return if (page <= 100){
            page++
        } else {
            page = 1
            page
        }
    }

    fun getMovieResults(): MutableList<MovieResponse> {
        getPopular()
        return resultsPopular
    }

}