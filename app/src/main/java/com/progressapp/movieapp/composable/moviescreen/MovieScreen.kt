package com.progressapp.movieapp.composable.moviescreen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.progressapp.movieapp.composable.ProgressIndicator
import com.progressapp.movieapp.composable.mainscreen.components.BottomBar
import com.progressapp.movieapp.composable.moviescreen.components.CustomDialog
import com.progressapp.movieapp.composable.moviescreen.components.RowText
import com.progressapp.movieapp.composable.moviescreen.components.TopBarMovie
import com.progressapp.movieapp.model.MovieDetailed
import com.progressapp.movieapp.ui.ViewModelMain

@Composable
fun MovieScreen(
    vm: ViewModelMain,
    selected: Int,
    backClicked: () -> Unit,
    accountCliked: () -> Unit,
    imageUrl: String = "https://image.tmdb.org/t/p/w500",
    modifier: Modifier = Modifier

){
    val movieSelected = vm.getResults()[selected]
    val scaffoldState = rememberScaffoldState()
    val isLoading = remember { vm.isLoading }
    val movieDetails = vm.getDetail(movieSelected.MovieId)
    var showDialog by remember { mutableStateOf(false) }
    var vote by remember { mutableStateOf(0.0) }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier,

        floatingActionButton = {
            FloatingActionButton(onClick = { println("something: " ) }) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Add to favourites", tint = Color.Red)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,

        topBar = {
            TopBarMovie(
                movieTitle = movieDetails.title,
                onBackClcked = backClicked,
                onProfileClicked = accountCliked)
        },
        bottomBar = {
            BottomBar(
                onHomeClicked = { /*TODO*/ },
                onMovieClicked = { /*TODO*/ },
                onFavoritesClicked = { /*TODO*/ },
                onSearchClicked = { /*TODO*/ })
        }
    
    ) {
        padding ->

        CustomDialog(onButtonAccept = {
            vote = it
            showDialog = false },
            onButtonDismis = { showDialog = false },
            show = showDialog)

        if (isLoading.value) {
            ProgressIndicator(modifier = Modifier.padding(padding))
        }
        else{
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                userScrollEnabled = true,
                contentPadding = PaddingValues(start = 10.dp, end = 10.dp, bottom = 45.dp)

            ) {

                item {
                    ImageView(imageUrl, movieDetails)
                }

                item {
                    InformationView(movieDetails) { showDialog = true }
                }
            }
        }
    }
}

@Composable
private fun InformationView(
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

@Composable
private fun SeeMoreText(
    text: String
){
    if (text.isBlank()) {
        Text(
            text = "No more information...",
            fontSize = 18.sp
        )
    }
    else {
        //From android.developer
        val annotatedText = buildAnnotatedString {
            pushStringAnnotation("URL", text)
            withStyle(style = SpanStyle(color = Color.Blue)){
                append("Home - WEBSITE")
            }
            pop()
        }

        val uriHandler = LocalUriHandler.current

        ClickableText(
            text = annotatedText,
            onClick = { offset ->
                // We check if there is an *URL* annotation attached to the text
                // at the clicked position
                annotatedText.getStringAnnotations(tag = "URL", start = offset,
                    end = offset)
                    .firstOrNull()?.let {
                            annotation ->
                                uriHandler.openUri(annotation.item)
                                Log.d("Clicked URL", annotation.item)
                    }
            }
        )
    }
}

@Composable
private fun DescriptionText(
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

@Composable
private fun TextTitle(
    text: String
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier) {
            Text(
                text = text,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            Divider(modifier = Modifier, color = Color.LightGray, 1.dp)
        }
    }   
} 

@Composable
private fun ImageView(
    imageUrl: String,
    movieDetailed: MovieDetailed
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = imageUrl + movieDetailed.poster_path,
            contentDescription = "Movie image",
            modifier = Modifier
                .shadow(20.dp, spotColor = Color.Black)
                .fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MovieScreenPreview(){
    //val movie = MovieResponse(false, "20.03.90",20,"Some title", "Some description", "/kqjL17yufvn9OVLyXYpvtyrFfak.jpg")
   // MovieScreen(0, {}, {})
}