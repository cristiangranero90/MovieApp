package com.progressapp.movieapp.composable.mainscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.progressapp.movieapp.composable.mainscreen.components.BottomBar
import com.progressapp.movieapp.composable.mainscreen.components.MovieItemView
import com.progressapp.movieapp.composable.mainscreen.components.TopBar
import com.progressapp.movieapp.model.MovieResponse
import com.progressapp.movieapp.ui.ViewModelMain

@Composable
fun MainScreen(
    viewModelMain: ViewModelMain = hiltViewModel()
){

    //viewModelMain.getPopular()
    val BASE_IMAGE_URL: String = "https://image.tmdb.org/t/p/w500"
    val moviesList = viewModelMain.getMovieResults()
    val moviesListString = mutableListOf<String>()
    val scaffoldState = rememberScaffoldState()

    //HarcodedMovieList
    for (i in 0..20){
        moviesListString.add(i, "https://via.placeholder.com/500")
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),

        topBar = {
            TopBar(onListClicked = { /*TODO*/ }, onAccountClicked = { /*TODO*/ })
        },
        bottomBar = {
            BottomBar(
                onHomeClicked = { /*TODO*/ },
                onMovieClicked = { /*TODO*/ },
                onFavoritesClicked = { /*TODO*/ },
                onSearchClicked = { /*TODO*/ })
        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()

        ){

            items(moviesList){
                MovieItemView(imageUrl = BASE_IMAGE_URL + it.movieImage, imageClicked = { /*TODO*/ })
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen()
}