package com.progressapp.movieapp.composable.moviescreen.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun DescriptionText(
    text: String
){
    if (text.isNullOrBlank()) {
        Text(
            text = "No description found...",
            fontSize = 18.sp
        )
    }
    else {
        Text(
            text = "    $text",
            fontSize = 18.sp
        )
    }
}