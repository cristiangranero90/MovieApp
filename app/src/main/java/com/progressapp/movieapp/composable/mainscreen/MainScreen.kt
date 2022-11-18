package com.progressapp.movieapp.composable.mainscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressapp.movieapp.composable.mainscreen.components.BottomBar
import com.progressapp.movieapp.composable.mainscreen.components.TopBar

@Composable
fun MainScreen(){

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),

        topBar = {
            TopBar(onListClicked = { /*TODO*/ }, onAccountClicked = { /*TODO*/ })
        },
        bottomBar = {
            BottomBar(
                onHomeClicked = { /*TODO*/ },
                onMovieclicked = { /*TODO*/ },
                onFavoritesClicked = { /*TODO*/ },
                onSearchClicked = { /*TODO*/ })
        }
    ) {



    }

}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen()
}