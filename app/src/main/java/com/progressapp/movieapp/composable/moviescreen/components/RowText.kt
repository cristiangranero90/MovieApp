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
    genres: String
    ){

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AddVote(vote)
        AddText(text = date)
        AddText(text = genres)
    }
}
@Composable
fun AddVote(
    vote: Double
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(2f))

    ) {

        if (vote < 7.0){
            Text(
                text = "Vote: $vote",
                color = Color.Red,
                fontWeight = FontWeight.Bold,
            )
        }
        else{
            Text(
                text = "Vote: $vote",
                fontWeight = FontWeight.Bold,
                color = Color.Green,
            )
        }
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
    RowText(vote = 123.0,"2", "horror"  )
}