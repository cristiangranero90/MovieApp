package com.progressapp.movieapp.composable.moviesviewscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressapp.movieapp.R

@Composable
fun TopBar(
    onListClicked: () -> Unit,
    onAccountClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        modifier = Modifier,
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 10.dp,

        title = {
            Image(
                painterResource(id = R.drawable.blue_long_small),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                contentDescription = "Movie image",
                contentScale = ContentScale.Crop
            )
        },

        navigationIcon = {
            IconButton(onClick = onListClicked) {
                Icon(imageVector = Icons.Default.List, contentDescription = "Side menu")
            }
        },

        actions = {
            IconButton(onClick = onAccountClicked) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Account settings")
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun TopBarPreview(){
    TopBar({},{})
}