package com.progressapp.movieapp.composable.moviescreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomDialog(
    onButtonAccept: (Double) -> Unit,
    onButtonDismis: () -> Unit,
    show: Boolean,
    modifier: Modifier = Modifier,
){

    if (show){
        var starsValue by remember {
            mutableStateOf(0.5f)
        }

        AlertDialog(onDismissRequest = { /*TODO*/ },

            confirmButton = {
                Button(onClick = { onButtonAccept(starsValue.toDouble()) }) {
                    Text(text = "Accept")
                }
            },
            dismissButton = {
                Button(onClick = { onButtonDismis() }) {
                    Text(text = "Cancel")
                }
            },
            title = {
                Image(
                    painterResource(id = com.progressapp.movieapp.R.drawable.ic_baseline_stars_24),
                    contentDescription = "background",
                    modifier = Modifier.fillMaxSize()
                )


                Text(text = "Vote this movie",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                ) },

            text = {
                Column {
                    Text(text = "Select the stars to vote: ",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Slider( value = starsValue ,onValueChange = { starsValue = it },
                        valueRange = 0.5f..10f,
                        steps = 10,
                    )
                    Text(text = "Stars: " + String.format("%.1f", starsValue),
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomDialogPreview(){
    CustomDialog( {}, {},true )
}