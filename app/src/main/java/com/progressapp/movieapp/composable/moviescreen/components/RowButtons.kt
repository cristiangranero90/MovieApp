package com.progressapp.movieapp.composable.moviescreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RowText(
    vote: Double,
    date: String,
    genres: List<Int>

    ){
    Row(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        AddText(text = "Vote: $vote")
        AddText(text = "Date:  $date")
        AddText(text = genres[0].toString())
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
            color = Color.White,
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true, backgroundColor = 20)
@Composable
fun RowTextPreview(){
    RowText(vote = 123.0,"2", listOf(23)  )
}