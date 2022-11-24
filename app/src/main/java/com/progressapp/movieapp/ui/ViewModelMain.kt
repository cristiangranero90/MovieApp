package com.progressapp.movieapp.ui

import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.progressapp.movieapp.model.MovieDetailed
import com.progressapp.movieapp.model.MovieResponse
import com.progressapp.movieapp.repositories.MovieRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ViewModelMain @Inject constructor(
    private val movieRepo : MovieRepositoryImp
) : ViewModel() {

    val isLoading = mutableStateOf(false)
    private var lang: String = Locale.getDefault().language + "-" + Locale.getDefault().country
    private var page = 1
    private val _results = mutableStateListOf<MovieResponse>()
    private val results = _results
    private var movieDetailed: MovieDetailed = getDetail(500)
    val barEnabled = mutableStateOf(true)

    private fun getPopular()  {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.value = true
            try{
                _results
                    .addAll(movieRepo
                    .getPopularMovies(lang,
                        getPage()
                        .toString())
                    .movieList)
            }
            catch (e: Exception ) {
                println(e.toString())
                Toast.makeText(null, "Internet problems", Toast.LENGTH_SHORT)
            }
            isLoading.value = false
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

    private fun getMovieDetails(id: Long){
        viewModelScope.launch(Dispatchers.Main) {
            print("Value of: " + isLoading.value)
            isLoading.value = true
            try {
                movieDetailed = movieRepo.getDetailedMovie(id, lang)
            }
            catch (e: Exception){
                println(e.toString())
            }
            isLoading.value = false
        }
    }

    fun getDetail(id: Long): MovieDetailed {
        getMovieDetails(id)
        return movieDetailed
    }

    fun getMovieResults(): MutableList<MovieResponse> {
        getPopular()
        return results
    }

    fun getResults() : MutableList<MovieResponse>{
        return results
    }
}

