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

    private var page = 1
    val isLoading = mutableStateOf(false)
    private val _results = mutableStateListOf<MovieResponse>()
    private val results = _results

    private fun getPopular()  {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try{
                _results.addAll(movieRepo.getPopularMovies(getPage().toString()).movieList)
            }
            catch (e: Exception ) {
                println(e.toString())
            }
        }
        isLoading.value = false
    }

    fun getMovieResults(): MutableList<MovieResponse> {
        getPopular()
        return results
    }

    fun getPage() : Int {
        if (page <= 100){
            return page++
        }
        else {
            page = 1
            return page
        }
    }
}

