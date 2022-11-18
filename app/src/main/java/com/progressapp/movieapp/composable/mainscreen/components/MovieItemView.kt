package com.progressapp.movieapp.composable.mainscreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun MovieItemView(
    imageUrl: String,
    imageClicked: () -> Unit,
    modifier: Modifier = Modifier
){

    Box(
        modifier.fillMaxSize().padding(1.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Movie image",
            modifier = Modifier.clickable {
                imageClicked
            }
                .fillMaxSize()
        )
    }

}

@Preview(showBackground = true)
@Composable
fun MovieItemViewPreview(){
    MovieItemView("https://via.placeholder.com/200", {})
}