package com.progressapp.movieapp.composable.homescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.progressapp.movieapp.composable.ProgressIndicator
import com.progressapp.movieapp.composable.homescreen.components.TopBar
import com.progressapp.movieapp.ui.ViewModelMain

@Composable
fun HomeScreen(
    bottomBar: @Composable () -> Unit,
    imageClicked: (Int) -> Unit,
    vm: ViewModelMain = hiltViewModel(),
    imageUrl: String = "https://image.tmdb.org/t/p/w500",
    modifier: Modifier = Modifier
){

    val popularItem = remember { vm.getMovieResults() }
    val upComingItem = remember { vm.getUpcoming() }
    val topRatedItem = remember { vm.getTopRated() }
    val nowPlayingItem = remember { vm.getNowPlaying() }
    val discoverItem = remember { vm.getDiscover() }
    val isLoading = remember { vm.isLoading }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        bottomBar = { bottomBar() },

    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(
                    start = 2.dp,
                    end = 2.dp,
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()),

        ){

            item {
                Text(text = "Popular movies",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center)

                LazyRow(modifier = Modifier.fillMaxWidth()){
                    items(popularItem){
                        if (isLoading.value){
                            ProgressIndicator()
                        }
                        else{
                            AsyncImage(
                                model = imageUrl + it.movieImage,
                                contentDescription = "Movie item",
                                modifier = Modifier
                                    .size(width = 150.dp, height = 250.dp)
                                    .clickable { imageClicked(it.MovieId.toInt()) }
                            )
                            Spacer(modifier = Modifier.size(6.dp))
                        }
                    }
                }
            }

            item {
                Text(text = "Upcoming",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center)

                LazyRow(modifier = Modifier.fillMaxWidth()){
                    items(upComingItem){
                        if (isLoading.value){
                            ProgressIndicator()
                        }
                        else{
                            AsyncImage(
                                model = imageUrl + it.movieImage,
                                contentDescription = "Movie item",
                                modifier = Modifier
                                    .size(width = 100.dp, height = 180.dp)
                                    .clickable { imageClicked(it.MovieId.toInt()) }
                            )
                            Spacer(modifier = Modifier.size(6.dp))
                        }
                    }
                }
            }

            item {
                Text(text = "Top Rated", fontSize = 28.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                LazyRow(modifier = Modifier.fillMaxWidth()){
                    items(topRatedItem){
                        if (isLoading.value){
                            ProgressIndicator()
                        }
                        else{
                            AsyncImage(
                                model = imageUrl + it.movieImage,
                                contentDescription = "Movie item",
                                modifier = Modifier
                                    .size(width = 100.dp, height = 180.dp)
                                    .clickable { imageClicked(it.MovieId.toInt()) }
                            )
                            Spacer(modifier = Modifier.size(6.dp))
                        }
                    }
                }
            }

            item {
                Text(text = "Discover", fontSize = 28.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                LazyRow(modifier = Modifier.fillMaxWidth()){
                    items(discoverItem){
                        if (isLoading.value){
                            ProgressIndicator()
                        }
                        else{
                            AsyncImage(
                                model = imageUrl + it.movieImage,
                                contentDescription = "Movie item",
                                modifier = Modifier
                                    .size(width = 100.dp, height = 180.dp)
                                    .clickable { imageClicked(it.MovieId.toInt()) }
                            )
                            Spacer(modifier = Modifier.size(6.dp))
                        }
                    }
                }
            }

            item {
                Text(text = "Now Playing on cinema",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center)

                LazyRow(modifier = Modifier.fillMaxWidth()){
                    items(nowPlayingItem){
                        if (isLoading.value){
                            ProgressIndicator()
                        }
                        else{
                            AsyncImage(
                                model = imageUrl + it.movieImage,
                                contentDescription = "Movie item",
                                modifier = Modifier
                                    .size(width = 150.dp, height = 250.dp)
                                    .clickable { imageClicked(it.MovieId.toInt()) }
                            )
                            Spacer(modifier = Modifier.size(6.dp))
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    //HomeScreen(hiltViewModel(), {}, {})
}