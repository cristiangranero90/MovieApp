package com.progressapp.movieapp.composable.favouritesscreen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Delete
import coil.compose.AsyncImage
import com.progressapp.movieapp.model.Movie

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieView(
    movie: Movie,
    movieClicked: () -> Unit,
    imageUrl: String  = "https://image.tmdb.org/t/p/w500"
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = imageUrl + movie.poster_path,
            contentDescription = "Movie image",
            modifier = Modifier
                .shadow(20.dp, spotColor = Color.Black)
                .fillMaxSize()
                .clickable { movieClicked() } ,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MovieViewPreview(){
    //MovieView()
}