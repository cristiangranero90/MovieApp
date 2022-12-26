package com.progressapp.movieapp.composable.moviescreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.progressapp.movieapp.model.MovieDetailed

@Composable
fun InformationView(
    movieSelected: MovieDetailed,
    voteClicked: () -> Unit,
) {
    Column {

        Spacer(modifier = Modifier.size(10.dp))

        RowText(
            vote = movieSelected.vote_average,
            date = movieSelected.release_date,
            genres = if (movieSelected.genres.isNotEmpty()) movieSelected.genres[0].name else "No genre"
        )

        Spacer(modifier = Modifier.size(10.dp))

        //Vote button
        Button(onClick = { voteClicked() }) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Vote", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(modifier = Modifier.size(5.dp))
                Icon(imageVector = Icons.Default.ThumbUp , contentDescription = "Vote")
            }
        }

        Spacer(modifier = Modifier.size(10.dp))
        /* TODO: Available on:
        TextTitle(text = "Available On")
        Spacer(modifier = Modifier.size(10.dp)) */

        TextTitle(text = "Overview ")

        Spacer(modifier = Modifier.size(10.dp))

        DescriptionText(text = movieSelected.overview)

        Spacer(modifier = Modifier.size(10.dp))

        TextTitle(text = "See More")

        Spacer(modifier = Modifier.size(10.dp))

        SeeMoreText(text = movieSelected.homepage)

    }
}