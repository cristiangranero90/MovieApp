package com.progressapp.movieapp.composable.favouritesscreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBarFavourites(
    onBackClicked: () -> Unit,
    onDeleteClicked: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var isContextual by remember { mutableStateOf(false) }
    val backgroundColor = if(isContextual) { Color.DarkGray } else { MaterialTheme.colors.primary}
    val contentColor = if(isContextual) {Color.White} else {Color.White}
    val title = if(isContextual) { "Select to delete..."} else { "Favourites"}

    TopAppBar(
        modifier = Modifier,
        backgroundColor = backgroundColor,
        elevation = 10.dp,

        title = {
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive,
                textAlign = TextAlign.Center,
                color = contentColor,
                modifier = Modifier.fillMaxWidth()
            )
        },

        navigationIcon = {
            if (isContextual){
                IconButton(onClick = {
                    isContextual = !isContextual
                    onDeleteClicked(isContextual)
                }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Go Back")
                }
            }
            else{
                IconButton(onClick = onBackClicked) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go Back")
                }
            }
        },

        actions = {
            IconButton(onClick = {
                isContextual = !isContextual
                onDeleteClicked(isContextual)
            } ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete movies"
                )
            }
        }
    )
}

