package com.progressapp.movieapp.composable.mainscreen

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
import com.progressapp.movieapp.composable.ProgressIndicator
import com.progressapp.movieapp.composable.mainscreen.components.MovieItemView
import com.progressapp.movieapp.composable.mainscreen.components.TopBar
import com.progressapp.movieapp.ui.ViewModelMain

@Composable
fun MainScreen(
    bottomNav: @Composable () -> Unit,
    viewModelMain: ViewModelMain,
    imageClicked: (Int) -> Unit,
    BASE_IMAGE_URL: String = "https://image.tmdb.org/t/p/w500",
    modifier: Modifier = Modifier
){
    val isLoading = remember { viewModelMain.isLoading }
    val moviesList = remember { viewModelMain.getMovieResults() }
    val scaffoldState = rememberScaffoldState()
    val gridState = rememberLazyGridState()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),

        topBar = {
            TopBar(
                onListClicked = { /*TODO*/ },
                onAccountClicked = { /*TODO*/ })
        },

        bottomBar =  { bottomNav() } ,
    ) {
        padding ->


        if (isLoading.value){
            ProgressIndicator(modifier = Modifier.padding(padding))
        }
        else{
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                state = gridState

            ){

                items(moviesList){
                    //if (!it.adultType){
                        MovieItemView(imageUrl = BASE_IMAGE_URL + it.movieImage,
                            imageClicked = {
                                imageClicked(it.MovieId.toInt())
                            })
                    //}
                    if(moviesList.indexOf(it) == moviesList.size-1){
                        viewModelMain.getMovieResults()
                    }
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