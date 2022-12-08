package com.progressapp.movieapp.composable.moviesviewscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.progressapp.movieapp.composable.ProgressIndicator
import com.progressapp.movieapp.composable.moviesviewscreen.components.MovieItemView
import com.progressapp.movieapp.composable.moviesviewscreen.components.TopBarMtv
import com.progressapp.movieapp.ui.ViewModelAll

@Composable
fun MoviesViewScreen(
    bottomNav: @Composable () -> Unit,
    imageClicked: (Int) -> Unit,
    onBackClicked: () -> Unit,
    vm: ViewModelAll = hiltViewModel(),
    BASE_IMAGE_URL: String = "https://image.tmdb.org/t/p/w500",
    modifier: Modifier = Modifier
){

    val isLoading = remember { vm.isLoading() }
    val moviesList = remember { vm.getMovieResults() }
    val scaffoldState = rememberScaffoldState()
    val gridState = rememberLazyGridState()

    if(isLoading || moviesList.isEmpty()){
        ProgressIndicator()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),

        topBar = {
            TopBarMtv {
                onBackClicked()
            }
        },

        bottomBar =  { bottomNav() } ,
    ) {

        padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            state = gridState

        ){
            items(moviesList){
                MovieItemView(imageUrl = BASE_IMAGE_URL + it.movieImage,
                    imageClicked = {
                        imageClicked(it.MovieId.toInt())
                    })

                if(moviesList.indexOf(it) == moviesList.size-1){
                    vm.getMovieResults()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    //MainScreen()
}