package com.progressapp.movieapp.composable

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.progressapp.movieapp.composable.mainscreen.MainScreen
import com.progressapp.movieapp.composable.splashscreen.SplashScreen

@Composable
fun Navigation(

) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash_screen") {

        composable("splash_screen") {
            SplashScreen(navController = navController)
        }

        composable("home_screen") {
            MainScreen({},{},{})
        }
    }
}