package com.progressapp.movieapp.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProgressIndicator(){

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center,

            ) {
            CircularProgressIndicator(
                modifier = Modifier.size(60.dp),
                color = Color.Cyan,
                strokeWidth = 6.dp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressIndicatorPreview(){
    ProgressIndicator()
}