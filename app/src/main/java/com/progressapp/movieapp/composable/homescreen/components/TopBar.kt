package com.progressapp.movieapp.composable.homescreen.components

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
    )
}


@Preview(showBackground = true)
@Composable
fun TopBarPreview(){
    TopBar()
}