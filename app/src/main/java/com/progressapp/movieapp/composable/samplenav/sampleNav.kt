package com.progressapp.movieapp.composable.samplenav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Sample(
    modifier: Modifier = Modifier,
){
    Column(
        modifier = Modifier.fillMaxSize(),

    ) {
        Text(text = "Something")
        Text(text = "Something")
        Text(text = "Something")
        Text(text = "Something")
        Text(text = "Something")
        Text(text = "Something")

    }

}

@Preview(showBackground = true)
@Composable
fun SamplePreview(){
    Sample()
}
