package com.progressapp.movieapp.ui

import android.util.Log
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

    var response: MovieList? = null

    private fun getPopular()  {
         viewModelScope.launch(Dispatchers.IO) {
            response = movieRepo.getPopularMovies()
        }
    }

    public fun clickPopular() {

        getPopular()

        if (response != null){
            val ite = response!!.movieList.iterator()
            while (ite.hasNext()){
                println(ite.next().originalTitle)
            }
        }
    }
}