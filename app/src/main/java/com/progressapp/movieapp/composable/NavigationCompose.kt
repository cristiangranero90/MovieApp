package com.progressapp.movieapp.composable

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.progressapp.movieapp.composable.mainscreen.MainScreen
import com.progressapp.movieapp.composable.moviescreen.MovieScreen
import com.progressapp.movieapp.composable.samplenav.Sample
import com.progressapp.movieapp.composable.splashscreen.SplashScreen
import com.progressapp.movieapp.ui.ViewModelMain

@Composable
fun Navigation(

) {
    val navController = rememberNavController()
    val viewModelMain = hiltViewModel<ViewModelMain>()

    NavHost(navController = navController, startDestination = "splash_screen") {

        composable("splash_screen") {
            SplashScreen(navController = navController)
        }

        composable("home_screen") {

            MainScreen(navController = navController, viewModelMain)
        }

        composable("sample_screen"){
            Sample()
        }

        composable(
            "movie_screen/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType} )
        ){
            backStackEntry ->
            MovieScreen(
                viewModelMain,
                selected = backStackEntry.arguments!!.getInt("id"),
                backClicked = { /*TODO*/ },
                accountCliked = { /*TODO*/ })

        }
    }
}