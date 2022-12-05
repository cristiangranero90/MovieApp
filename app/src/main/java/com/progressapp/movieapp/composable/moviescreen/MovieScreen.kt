package com.progressapp.movieapp.composable.moviescreen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.progressapp.movieapp.composable.ProgressIndicator
import com.progressapp.movieapp.composable.moviescreen.components.*
import com.progressapp.movieapp.ui.ViewModelMovies

@Composable
fun MovieScreen(
    bottomNav: @Composable () -> Unit,
    selected: Int,
    backClicked: () -> Unit,
    accountCliked: () -> Unit,
    vm: ViewModelMovies = hiltViewModel(),
    imageUrl: String = "https://image.tmdb.org/t/p/w500",
    modifier: Modifier = Modifier

){
    val scaffoldState = rememberScaffoldState()
    val isLoading = remember { vm.isLoading() }
    val movieDetails = vm.getDetail(selected.toLong())
    var showDialog by remember { mutableStateOf(false) }
    var vote by remember { mutableStateOf(0.0) }
    val context = LocalContext.current

    if (isLoading){
        ProgressIndicator()
    }
    else if(movieDetails == null){
        ProgressIndicator()
    }
    else{
        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier,

            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        vm.addFavourite(movieDetails)
                        Toast.makeText(context,"Added to favourites", Toast.LENGTH_SHORT ).show()
                    }) {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = "Add to favourites", tint = Color.Red)
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true,

            topBar = {
                TopBarMovie(
                    movieTitle = movieDetails.title,
                    onBackClcked = backClicked,
                    onProfileClicked = accountCliked)
            },
            bottomBar = { bottomNav () }

        ) {
                padding ->

            CustomDialog(onButtonAccept = {
                vote = it
                showDialog = false },
                onButtonDismis = { showDialog = false },
                show = showDialog)

            LazyColumn(
                modifier = Modifier
                    .padding(
                        start = 2.dp,
                        end = 2.dp,
                        top = padding.calculateTopPadding(),
                        bottom = padding.calculateBottomPadding()
                    )
                    .fillMaxSize(),
                contentPadding = padding
            ) {

                item {
                    ImageView(imageUrl, movieDetails)
                }

                item {
                    InformationView(movieDetails) { showDialog = true }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MovieScreenPreview(){
    //val movie = MovieResponse(false, "20.03.90",20,"Some title", "Some description", "/kqjL17yufvn9OVLyXYpvtyrFfak.jpg")
   // MovieScreen(0, {}, {})
}