package com.progressapp.movieapp.composable.mainscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomBar(
    onHomeClicked: () -> Unit,
    onMovieclicked: () -> Unit,
    onFavoritesClicked: () -> Unit,
    onSearchClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Row (
        modifier = Modifier.fillMaxWidth()
            .background(color = Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){

        IconButton(onClick = onHomeClicked) {
            Icon(imageVector = Icons.Default.Home, contentDescription = "Home Screen")
        }

        IconButton(onClick = onMovieclicked) {
            Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Movies")
        }

        IconButton(onClick = onFavoritesClicked) {
            Icon(imageVector = Icons.Default.Star, contentDescription = "Favorites")
        }

        IconButton(onClick = onSearchClicked) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search movie")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview(){
    BottomBar({},{},{},{})
}