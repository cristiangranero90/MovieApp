package com.progressapp.movieapp.composable.moviescreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun RowText(
    vote: Double,
    date: String,
    genres: String,
    ){

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AddVote(vote)
        AddText(text = date)
        AddText(text = genres)
    }
}
@Composable
fun AddVote(
    vote: Double,
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
    ) {

        var colorSelected = when(vote){
            in 0.0..4.0 -> Color.Red
            in 4.1..6.9 -> Color(0xFFE45E34)
            else -> {
                Color.Green
            }
        }

        Text(
            text = "Vote: " + String.format("%.1f", vote).toDouble(),
            color = colorSelected,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun AddText(text: String){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(2f))

    ) {

        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true, backgroundColor = 20)
@Composable
fun RowTextPreview(){
    RowText(vote = 123.0,"2", "horror")
}