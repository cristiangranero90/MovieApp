package com.progressapp.movieapp.composable.mainscreen.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar(
    onHomeClicked: () -> Unit,
    onMovieClicked: () -> Unit,
    onFavoritesClicked: () -> Unit,
    onSearchClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    val selectedItem = remember { mutableStateOf(0) }

    BottomNavigation(
        modifier = Modifier,
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 10.dp,
    ) {
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home Screen") },
            label = { Text(text = "Home")},
            selected = (selectedItem.value == 0),
            onClick = {
                selectedItem.value = 0
                onHomeClicked()
            }
        )

        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Movies screen") },
            label = { Text(text = "Movies")},
            selected = (selectedItem.value == 1),
            onClick = {
                onMovieClicked()
                selectedItem.value = 1}
        )

        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Star, contentDescription = "Favorites") },
            label = { Text(text = "Favourites")},
            selected = (selectedItem.value == 2),
            onClick = {
                selectedItem.value = 2
                onFavoritesClicked()
            }
        )

        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search movie") },
            label = { Text(text = "Search")},
            selected = (selectedItem.value == 3),
            onClick = {
                selectedItem.value = 3
                onSearchClicked()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview(){
    BottomBar({},{},{},{})
}