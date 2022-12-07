package com.progressapp.movieapp.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
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

    private val movieDetailed = mutableStateListOf<MovieDetailed>()
    private val _isLoading = mutableStateOf(false)

    fun isLoading() = _isLoading.value

    fun addFavourite(movieDetailed: MovieDetailed)  {
        viewModelScope.launch(Dispatchers.Main) {
            movieRepo.addToFavourites(movieDetailed)
        }
    }

    private fun getMovieDetails(id: Long) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.Main) {
            try {
                movieDetailed.add(movieRepo.getDetailedMovie(id))
            }
            catch (e: Exception){
                println(e.toString())
            }
            _isLoading.value = false
        }
    }

    fun getDetail(id: Long): SnapshotStateList<MovieDetailed> {
        getMovieDetails(id)
        return movieDetailed
    }
}