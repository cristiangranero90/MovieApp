package com.progressapp.movieapp.composable.moviescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.progressapp.movieapp.composable.mainscreen.components.BottomBar
import com.progressapp.movieapp.composable.moviescreen.components.TopBarMovie
import com.progressapp.movieapp.model.MovieResponse

@Composable
fun MovieScreen(
    movieResponse: MovieResponse,
    backClicked: () -> Unit,
    accountCliked: () -> Unit,
    imageUrl: String = "https://image.tmdb.org/t/p/w500",
    modifier: Modifier = Modifier
){
    Scaffold(
        modifier = Modifier,
        topBar = {
            TopBarMovie(
                movieTitle = movieResponse.originalTitle,
                onBackClcked = backClicked,
                onProfileClicked = accountCliked)
        },
        bottomBar = {
            BottomBar(
                onHomeClicked = { /*TODO*/ },
                onMovieClicked = { /*TODO*/ },
                onFavoritesClicked = { /*TODO*/ },
                onSearchClicked = { /*TODO*/ })
        }
    
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {

            AsyncImage(
                model = imageUrl + movieResponse.movieImage,
                contentDescription = "Movie image",
                modifier = Modifier.fillMaxWidth()
            )
            
            Text(text = movieResponse.movieDescription, fontSize = 18.sp)

        }
        
    }
    
    


}

@Preview(showBackground = true)
@Composable
fun MovieScreenPreview(){
    val movie = MovieResponse(false, "20.03.90",20,"Some title", "Some description", "/kqjL17yufvn9OVLyXYpvtyrFfak.jpg")
    MovieScreen(movie, {}, {})
}