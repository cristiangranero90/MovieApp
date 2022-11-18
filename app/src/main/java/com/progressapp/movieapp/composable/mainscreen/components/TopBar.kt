package com.progressapp.movieapp.composable.mainscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressapp.movieapp.R

@Composable
fun TopBar(
    onListClicked: () -> Unit,
    onAccountClicked: () -> Unit,
    modifier: Modifier = Modifier
){

    Row(
        modifier = Modifier.fillMaxWidth()
            .background(color = Color.White),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,


    ) {

        IconButton(onClick = onListClicked) {
            Icon(imageVector = Icons.Default.List, contentDescription = "List")
        }

        Box(
            modifier = Modifier.padding(start = 2.dp, end = 8.dp, top = 20.dp, bottom = 20.dp)
                .width(300.dp)
        ) {
            Image(
                painterResource(id = R.drawable.blue_long_small),
                modifier = Modifier.fillMaxWidth(),
                contentDescription = "Image",
                contentScale = ContentScale.Crop
                )
        }

        IconButton(onClick = onAccountClicked) {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Account")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview(){
    TopBar({},{})
}