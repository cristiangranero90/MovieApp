package com.progressapp.movieapp.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.progressapp.movieapp.model.MovieResponse
import com.progressapp.movieapp.repositories.MovieRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelSearch @Inject constructor(
    private val movieRepo: MovieRepositoryImp
) : ViewModel() {

    private val _isLoading = mutableStateOf(false)
    private val _resultsSearch = mutableStateListOf<MovieResponse>()
    var searchText = mutableStateOf("")
    var startSearch = mutableStateOf(searchText.value.length > 3)
    var resultsSearch = _resultsSearch

    fun isLoading() = _isLoading.value

    fun searchMovie(toSearch: String) {
        _isLoading.value = true
        resultsSearch.clear() //Necessary
        viewModelScope.launch(Dispatchers.IO) {
           _resultsSearch.addAll(0, movieRepo.searchMovie(toSearch).movieList)
        }
        _isLoading.value = false
    }
}