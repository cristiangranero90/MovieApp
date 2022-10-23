package com.progressapp.movieapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressapp.movieapp.model.MovieResponse
import com.progressapp.movieapp.data.RestDataSource
import com.progressapp.movieapp.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                    getLatestMovie()
                }
            }
        }
    }
    

    private fun getRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getLatestMovie() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(RestDataSource::class.java)
                .getPopularMovies()
            val movies = call.body() as MovieResponse

            if  (call.isSuccessful) {
                showOnLog(movies)
            }
            else{
                showErrorLog()
            }
        }
    }

    fun showErrorLog() {
        Toast.makeText(this, "unexpected error", Toast.LENGTH_SHORT)
    }


    fun showOnLog(movies : MovieResponse) {
        Log.i("Tittle ", movies.originalTitle)
        Log.i("Description ", movies.movieDescription)
        Log.i("Date ", if (movies.relaseDate.isNullOrBlank()) "Non released yet" else movies.relaseDate)
        Log.i("Movie ID ", movies.MovieId.toString())
        Log.i("Adult Type: ", movies.adultType.toString())
        Log.i("Image path:", if (movies.movieImage.isNullOrBlank()) "No image found" else movies.movieImage)

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAppTheme {
        Greeting("Android")
    }
}

