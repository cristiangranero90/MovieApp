package com.progressapp.movieapp.composable.moviescreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomDialog(
    onButtonAccept: (Double) -> Unit,
    onButtonDismis: () -> Unit,
    show: Boolean,
    modifier: Modifier = Modifier,
){

    if (show){
        var starsValue by remember {
            mutableStateOf(5f)
        }

        AlertDialog(onDismissRequest = { onButtonDismis() },

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
                    modifier = Modifier.fillMaxSize(),
                )

                Text(text = "Rate this movie",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    modifier = Modifier.fillMaxWidth()
                ) },

            text = {
                Column {
                    //Spacer(modifier = Modifier.size(10.dp))
                    Text(text = "Select your vote: ",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.size(30.dp))
                    Text(text = "Stars: " + String.format("%.1f", starsValue),
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Slider( value = starsValue ,onValueChange = { starsValue = it },
                        valueRange = 1f..10f,
                        steps = 10,
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