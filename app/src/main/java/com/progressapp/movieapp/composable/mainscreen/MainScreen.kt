package com.progressapp.movieapp.composable.mainscreen

import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavController
import com.progressapp.movieapp.composable.ProgressIndicator
import com.progressapp.movieapp.composable.mainscreen.components.BottomBar
import com.progressapp.movieapp.composable.mainscreen.components.MovieItemView
import com.progressapp.movieapp.composable.mainscreen.components.TopBar
import com.progressapp.movieapp.composable.moviescreen.components.TopBarMovie
import com.progressapp.movieapp.ui.ViewModelMain

@Composable
fun MainScreen(
    navController: NavController,
    viewModelMain: ViewModelMain,
    BASE_IMAGE_URL: String = "https://image.tmdb.org/t/p/w500",
    modifier: Modifier = Modifier
){
    val isLoading = remember { viewModelMain.isLoading }
    val moviesList = remember { viewModelMain.getMovieResults() }
    val enableBar = remember { viewModelMain.barEnabled }
    val scaffoldState = rememberScaffoldState()
    val gridState = rememberLazyGridState()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),

        topBar = {
            if(enableBar.value){
                TopBar(
                    onListClicked = { /*TODO*/ },
                    onAccountClicked = {  } )
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
                onHomeClicked = { navController.navigate("home_screen") { launchSingleTop = true } },
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
                        imageClicked = {
                            print("I am clicked")
                            navController.navigate("movie_screen/${moviesList.indexOf(it)}" ) { popUpTo("home_screen")}
                        })
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
    //MainScreen()
}