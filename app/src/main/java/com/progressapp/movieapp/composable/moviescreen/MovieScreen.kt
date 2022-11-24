package com.progressapp.movieapp.composable.moviescreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.progressapp.movieapp.composable.ProgressIndicator
import com.progressapp.movieapp.composable.mainscreen.components.BottomBar
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

        if (isLoading.value) {
            ProgressIndicator()
        }

        else{
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                userScrollEnabled = true,
                contentPadding = PaddingValues(start = 10.dp, end = 10.dp, bottom = 45.dp)

            ) {

                item {
                    ImageView(imageUrl, movieDetails)
                }

                item {
                    InformationView(movieDetails)
                }
            }
        }

    }
}

@Composable
private fun InformationView(
    movieSelected: MovieDetailed
) {
    Column() {

        Spacer(modifier = Modifier.size(10.dp))

        RowText(
            vote = movieSelected.vote_average,
            date = movieSelected.release_date,
            genres = movieSelected.genres[0].name
        )

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = "Description: ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(10.dp))

        if (movieSelected.overview.isNullOrBlank()) {
            Text(
                text = "No description found...",
                fontSize = 18.sp
            )
        } else {
            Text(
                text = "    " + movieSelected.overview,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.size(30.dp))
    }
}

@Composable
private fun ImageView(
    imageUrl: String,
    movieSelected: MovieDetailed
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = imageUrl + movieSelected.poster_path,
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