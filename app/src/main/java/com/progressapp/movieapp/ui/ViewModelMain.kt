package com.progressapp.movieapp.ui

import androidx.compose.ui.input.key.Key.Companion.Sleep
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.progressapp.movieapp.model.MovieList
import com.progressapp.movieapp.model.MovieResponse
import com.progressapp.movieapp.repositories.MovieRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class ViewModelMain @Inject constructor(
    private val movieRepo : MovieRepositoryImp
) : ViewModel() {

    val results = mutableListOf<MovieResponse>()
    val isLoading : LiveData<Boolean> get() = _isLoading

    private val _isLoading : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }


    fun getPopular()  {
         viewModelScope.launch(Dispatchers.IO) {
             _isLoading.postValue(true)
             results.addAll(movieRepo.getPopularMovies()!!.movieList)
             _isLoading.postValue(false)
        }
    }

    fun getMovieResults(): MutableList<MovieResponse> {
        getPopular()
        Thread.sleep(800)
        return results
    }


}