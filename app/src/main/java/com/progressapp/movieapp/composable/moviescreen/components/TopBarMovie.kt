package com.progressapp.movieapp.composable.moviescreen.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBarMovie(
    movieTitle: String,
    onBackClcked: () -> Unit,
    onProfileClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        modifier = Modifier,
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 10.dp,

        title = {
            Text(text = movieTitle,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        },

        navigationIcon = {
            IconButton(onClick = onBackClcked) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go Back")
            }
        },

        actions = {
            IconButton(onClick = onProfileClicked) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Profile settings")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarMoviePreview(){
    TopBarMovie("Some title", {}, {})

}