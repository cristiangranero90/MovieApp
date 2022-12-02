package com.progressapp.movieapp.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.progressapp.movieapp.model.Movie
import com.progressapp.movieapp.repositories.MovieRepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelFavourites @Inject constructor(
    private val movieRepo : MovieRepositoryImp
) : ViewModel(){

    private val _isLoading = mutableStateOf(false)
    private val _allFavourites = mutableStateListOf<Movie>()
    val allFavourites = _allFavourites

    fun isLoading() = _isLoading.value

    private fun getAll(): SnapshotStateList<Movie> {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.Main) {
            _allFavourites.addAll(0, movieRepo.getAllMovies())
            _isLoading.value = false
        }
        return allFavourites
    }

    private suspend fun addFavourite(id: Int){
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            movieRepo.addToFavourites(movieRepo.getDetailedMovie(id.toLong()))
            _isLoading.value = false
        }
    }

}