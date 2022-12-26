package com.progressapp.movieapp.composable.searchscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.progressapp.movieapp.ui.ViewModelSearch
import com.progressapp.movieapp.composable.ProgressIndicator
import com.progressapp.movieapp.composable.moviesviewscreen.components.MovieItemView
import com.progressapp.movieapp.composable.searchscreen.components.TopSearchBar


@Composable
fun SearchScreen(
    BottomNav: @Composable () -> Unit,
    onBackClicked: () -> Unit,
    imageClicked: (Int) -> Unit,
    BASE_IMAGE_URL: String = "https://image.tmdb.org/t/p/w500",
    vm: ViewModelSearch = hiltViewModel(),
) {
    var search by remember { vm.searchText }
    val startSearch by remember { vm.startSearch }
    val searchResults = remember { vm.resultsSearch }
    val isLoading = remember { vm.isLoading() }

    if(search.length >= 3 || startSearch){
        vm.searchMovie(search)
    }

    Scaffold(
        modifier = Modifier,
        topBar = { TopSearchBar(onBackClicked, onSearchClicked = { search = it }, vm.searchText)  },
        bottomBar = { BottomNav() }

    ) { paddingValues ->

        if(search.length < 3){
            Column(
                modifier = Modifier.padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Type to search. No results...",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.fillMaxWidth(),
                    )
            }
        }

        else if (!isLoading){
            Row(modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()) {
                Text(
                    text = "Results for: $search",
                    style = MaterialTheme.typography.h6
                    )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(top = 30.dp, end = 2.dp, bottom = paddingValues.calculateBottomPadding(), start = 2.dp)
            ){

                items(searchResults.size){
                    MovieItemView(
                        imageUrl = BASE_IMAGE_URL + searchResults[it].movieImage,
                        imageClicked = { imageClicked(searchResults[it].MovieId.toInt()) })
                }
            }
        }
        else{
            ProgressIndicator(Modifier.padding(paddingValues))
        }
    }
}



@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen({}, {}, {})
}