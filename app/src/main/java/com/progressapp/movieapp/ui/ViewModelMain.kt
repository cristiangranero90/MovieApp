package com.progressapp.movieapp.ui

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

    val isLoading = mutableStateOf(false)
    private val results = mutableListOf<MovieResponse>()

    private fun getPopular()  {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            results.addAll(movieRepo.getPopularMovies().movieList)
        }
        isLoading.value = false
    }

    fun getMovieResults(): MutableList<MovieResponse> {
        getPopular()
        Thread.sleep(800)
        return results
    }
}