package com.progressapp.movieapp.composable.moviescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.progressapp.movieapp.composable.mainscreen.components.BottomBar
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
    print("El numero que llego bueyu $selected")

    val movieSelected = vm.getResults()[selected]
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier,
        topBar = {
            TopBarMovie(
                movieTitle = movieSelected.originalTitle,
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

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(300.dp),
                contentAlignment = Alignment.BottomStart,
            ) {
                AsyncImage(
                    model = imageUrl + movieSelected.movieImage,
                    contentDescription = "Movie image",
                    modifier = Modifier.fillMaxWidth()
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Star,
                        contentDescription = "Add to favorites",
                    modifier = Modifier.clip(CircleShape).size(60.dp).background(Color.Yellow))
                }
            }

            Spacer(modifier = Modifier.size(10.dp))

            Text(text = movieSelected.originalTitle,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.size(10.dp))

            Text(text = movieSelected.movieDescription,
                fontSize = 18.sp)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieScreenPreview(){
    //val movie = MovieResponse(false, "20.03.90",20,"Some title", "Some description", "/kqjL17yufvn9OVLyXYpvtyrFfak.jpg")
   // MovieScreen(0, {}, {})
}