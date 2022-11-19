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
import androidx.navigation.NavController
import com.progressapp.movieapp.composable.ProgressIndicator
import com.progressapp.movieapp.composable.mainscreen.components.BottomBar
import com.progressapp.movieapp.composable.mainscreen.components.MovieItemView
import com.progressapp.movieapp.composable.mainscreen.components.TopBar
import com.progressapp.movieapp.ui.ViewModelMain

@Composable
fun MainScreen(
    imageClicked: () -> Unit,
    backClicked: () -> Unit,
    accountClicked: () -> Unit,
    viewModelMain: ViewModelMain = hiltViewModel(),
    BASE_IMAGE_URL: String = "https://image.tmdb.org/t/p/w500",
    modifier: Modifier = Modifier
){
    val isLoading = viewModelMain.isLoading.value
    val moviesList = viewModelMain.getMovieResults()
    val moviesListString = mutableListOf<String>()
    val scaffoldState = rememberScaffoldState()

    //Hardcoded MovieList for some tests
    for (i in 0..20){
        moviesListString.add(i, "https://via.placeholder.com/500")
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),

        topBar = {
            TopBar(onListClicked = { /*TODO*/ }, onAccountClicked = accountClicked)
        },

        bottomBar = {
            BottomBar(
                onHomeClicked = { /*TODO*/ },
                onMovieClicked = { /*TODO*/ },
                onFavoritesClicked = { /*TODO*/ },
                onSearchClicked = { /*TODO*/ })
        }
    ) {

        ProgressIndicator(showIndicator = isLoading)

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()

        ){
            items(moviesList){
                MovieItemView(imageUrl = BASE_IMAGE_URL + it.movieImage,
                    imageClicked = { })
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen({},{}, {})
}