package com.progressapp.movieapp.composable.favouritesscreen

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.progressapp.movieapp.composable.ProgressIndicator
import com.progressapp.movieapp.composable.favouritesscreen.components.MovieEmpty
import com.progressapp.movieapp.composable.favouritesscreen.components.MovieView
import com.progressapp.movieapp.composable.favouritesscreen.components.TopBarFavourites
import com.progressapp.movieapp.ui.ViewModelFavourites

@Composable
fun FavouritesScreen(
    bottomNav: @Composable () -> Unit,
    onBackClicked: () -> Unit,
    movieClicked: (Int) -> Unit,
    vm: ViewModelFavourites = hiltViewModel(),
){
    val favourites = remember { vm.allFavourites }
    var contextualDelete by remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (vm.isLoading()){
        ProgressIndicator()
    }
    if(favourites.isEmpty()){
        MovieEmpty()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarFavourites(
            onBackClicked = { onBackClicked() },
            onDeleteClicked = { contextualDelete = it } )},
        bottomBar = {
            bottomNav() }
    ) {
        paddingValues ->

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 2.dp,
                    end = 2.dp,
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                ),
            columns = GridCells.Fixed(2)
        ){
            if (contextualDelete){
                items(favourites){
                    MovieView(
                        movie = it,
                        movieClicked = {
                            vm.deleteMovie(it)
                            Toast.makeText(context,"${it.title} deleted", Toast.LENGTH_SHORT).show()
                        },
                    )
                }
            }
            else{
                items(favourites){ it ->
                    MovieView(
                        movie = it,
                        movieClicked = { movieClicked(it.idtmdb )},
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavouritesScreenPreview(){

}