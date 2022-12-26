package com.progressapp.movieapp.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.progressapp.movieapp.composable.favouritesscreen.FavouritesScreen
import com.progressapp.movieapp.composable.homescreen.HomeScreen
import com.progressapp.movieapp.composable.moviesviewscreen.MoviesViewScreen
import com.progressapp.movieapp.composable.moviesviewscreen.components.BottomBar
import com.progressapp.movieapp.composable.moviescreen.MovieScreen
import com.progressapp.movieapp.composable.searchscreen.SearchScreen
import com.progressapp.movieapp.composable.splashscreen.SplashScreen

@Composable
fun Navigation(

) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomNav: @Composable () -> Unit = {
        BottomBar(
            currentDestination,
            onHomeClicked = { navController.navigate("home_screen") {
                popUpTo("home_screen")
                    launchSingleTop = true
                    restoreState = true
                }
            },
            onMovieClicked = {
                navController.navigate("movies_view_screen") {
                    popUpTo("movies_view_screen")
                    launchSingleTop = true
                    restoreState = true
                }
            },
            onFavoritesClicked = {
                navController.navigate("favourites_screen") {
                    launchSingleTop = true
                    restoreState = true
                }
            },
            onSearchClicked = {
                navController.navigate("search_screen") {
                launchSingleTop = true
                restoreState = true
                }
            })
    }


    NavHost(navController = navController, startDestination = "splash_screen") {

        composable("splash_screen") {
            SplashScreen(navController = navController)
        }

        composable("home_screen") {
            HomeScreen(
                bottomBar = bottomNav,
                { navController.navigate("movie_screen/$it")}
            )
        }

        composable("movies_view_screen"){
            MoviesViewScreen(
                bottomNav,
                { navController.navigate("movie_screen/$it")},
                { navController.navigateUp() }
            )
        }

        composable("favourites_screen"){
            FavouritesScreen(
                bottomNav = bottomNav,
                onBackClicked = { navController.navigateUp() } ,
                { navController.navigate("movie_screen/$it") })
        }

        composable("search_screen"){
            SearchScreen(
                bottomNav,
                onBackClicked = { navController.navigateUp() },
                { navController.navigate("movie_screen/$it") }
                )
        }

        composable(
            "movie_screen/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType} )
        ){
            backStackEntry ->
            MovieScreen(
                bottomNav,
                selected = backStackEntry.arguments!!.getInt("id"),
                backClicked = { navController.navigateUp() },
                accountClicked = { /*TODO*/ })

        }
    }
}