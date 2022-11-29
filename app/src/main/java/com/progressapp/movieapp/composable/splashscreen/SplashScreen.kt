package com.progressapp.movieapp.composable.splashscreen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.progressapp.movieapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){

    val scale = remember {
        Animatable(0f)
    }

    //Image scale animation
    LaunchedEffect(key1 = true ) {
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate("home_screen") { popUpTo("splash_screen") }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(text = "Powered by",
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp)



        Box(
            modifier = Modifier.size(500.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painterResource(id = R.drawable.blue_square_splash),
                contentDescription = "Splash logo",
                modifier = Modifier
                    .scale(scale.value)
                    .fillMaxSize()
            )
        }
    }
}


