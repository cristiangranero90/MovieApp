package com.progressapp.movieapp.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.progressapp.movieapp.model.MovieDetailed
import com.progressapp.movieapp.repositories.MovieRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelMovies @Inject constructor(
    private val movieRepo : MovieRepositoryImp
) : ViewModel(){

    private var movieDetailed: MovieDetailed = getDetail(500)
    private val _isLoading = mutableStateOf(false)

    fun isLoading() = _isLoading.value

    fun addFavourite(movieDetailed: MovieDetailed)  {
        viewModelScope.launch(Dispatchers.Main) {
            movieRepo.addToFavourites(movieDetailed)
        }
    }

    private fun getMovieDetails(id: Long){
        viewModelScope.launch(Dispatchers.Main) {
            _isLoading.value = true
            try {
                movieDetailed = movieRepo.getDetailedMovie(id)
            }
            catch (e: Exception){
                println(e.toString())
            }
            _isLoading.value = false || (movieDetailed.id != id.toInt())
        }
    }

    fun getDetail(id: Long): MovieDetailed {
        getMovieDetails(id)
        return movieDetailed
    }
}