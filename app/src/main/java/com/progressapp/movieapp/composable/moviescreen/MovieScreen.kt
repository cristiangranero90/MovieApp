package com.progressapp.movieapp.composable.moviescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.progressapp.movieapp.composable.mainscreen.components.BottomBar
import com.progressapp.movieapp.composable.moviescreen.components.RowText
import com.progressapp.movieapp.composable.moviescreen.components.TopBarMovie
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

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier,

        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Add to favourites")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,

        topBar = {
            TopBarMovie(
                movieTitle = movieSelected.title,
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

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            userScrollEnabled = true,
            contentPadding = PaddingValues(start = 10.dp, end = 10.dp, bottom = 45.dp)

        ) {

            item {

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    AsyncImage(
                        model = imageUrl + movieSelected.movieImage,
                        contentDescription = "Movie image",
                        modifier = Modifier
                            .shadow(20.dp, spotColor = Color.Black)
                            .fillMaxSize(),
                        contentScale = ContentScale.FillWidth
                    )
                    RowText(
                        vote = movieSelected.punctuation,
                        date = movieSelected.relaseDate,
                        genres = movieSelected.genre)
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {

                    Column() {

                        Spacer(modifier = Modifier.size(10.dp))

                        Text(
                            text = "Description: ",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.size(10.dp))

                        if (movieSelected.movieDescription.isNullOrBlank()){
                            Text(
                                text = "No description...",
                                fontSize = 18.sp
                            )
                        }
                        else{
                            Text(
                                text = "    " + movieSelected.movieDescription,
                                fontSize = 18.sp
                            )
                        }

                        Spacer(modifier = Modifier.size(30.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieScreenPreview(){
    //val movie = MovieResponse(false, "20.03.90",20,"Some title", "Some description", "/kqjL17yufvn9OVLyXYpvtyrFfak.jpg")
   // MovieScreen(0, {}, {})
}