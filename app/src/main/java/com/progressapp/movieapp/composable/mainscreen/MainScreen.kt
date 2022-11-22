package com.progressapp.movieapp.composable.mainscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.progressapp.movieapp.composable.ProgressIndicator
import com.progressapp.movieapp.composable.mainscreen.components.BottomBar
import com.progressapp.movieapp.composable.mainscreen.components.MovieItemView
import com.progressapp.movieapp.composable.mainscreen.components.TopBar
import com.progressapp.movieapp.composable.moviescreen.components.TopBarMovie
import com.progressapp.movieapp.model.MovieResponse
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
    val isLoading = remember { viewModelMain.isLoading }
    val moviesList = remember { viewModelMain.getMovieResults() }
    val scaffoldState = rememberScaffoldState()
    val gridState = rememberLazyGridState()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),

        topBar = {
            if(true){
                TopBar(
                    onListClicked = { /*TODO*/ },
                    onAccountClicked = accountClicked )
            }
            else{
                TopBarMovie(
                    movieTitle = "Some title",
                    onBackClcked = { /*TODO*/ },
                    onProfileClicked = { /*TODO*/ } )
            }
        },

        bottomBar = {
            BottomBar(
                onHomeClicked = { /*TODO*/ },
                onMovieClicked = { /*TODO*/ },
                onFavoritesClicked = { /*TODO*/ },
                onSearchClicked = { /*TODO*/ })
        }
    ) {

        ProgressIndicator(showIndicator = isLoading.value)

        LazyVerticalGrid(

            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize(),
            state = gridState

        ){
            items(moviesList){
                if (!it.adultType){
                    MovieItemView(imageUrl = BASE_IMAGE_URL + it.movieImage,
                        imageClicked = { })
                }
                if(moviesList.indexOf(it) == moviesList.size-1){
                    viewModelMain.getMovieResults()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen({},{}, {})
}