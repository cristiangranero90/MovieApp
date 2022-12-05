package com.progressapp.movieapp.composable.moviescreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.progressapp.movieapp.model.MovieDetailed

@Composable
fun ImageView(
    imageUrl: String,
    movieDetailed: MovieDetailed
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = imageUrl + movieDetailed.poster_path,
            contentDescription = "Movie image",
            modifier = Modifier
                .shadow(20.dp, spotColor = Color.Black)
                .fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
    }
}
