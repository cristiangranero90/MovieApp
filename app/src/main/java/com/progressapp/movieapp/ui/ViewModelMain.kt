package com.progressapp.movieapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.progressapp.movieapp.model.MovieList
import com.progressapp.movieapp.repositories.MovieRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelMain @Inject constructor(
    private val movieRepo : MovieRepositoryImp
) : ViewModel() {

    var results: MovieList? = null
    private val _isLoading : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val isLoading : LiveData<Boolean> get() = _isLoading

    fun getPopular()  {
         viewModelScope.launch(Dispatchers.IO) {
             _isLoading.postValue(true)
             results = movieRepo.getPopularMovies()
             _isLoading.postValue(false)
        }
    }

    fun clickPopular() {

        if (results != null){
            val ite = results!!.movieList.iterator()
            while (ite.hasNext()){
                println(ite.next().originalTitle)
            }
        }
        else{
            getPopular()
        }
    }


}