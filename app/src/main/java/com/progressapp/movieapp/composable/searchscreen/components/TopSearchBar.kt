package com.progressapp.movieapp.composable.searchscreen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TopSearchBar(
    onBackClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    searchText: MutableState<String>
){
    var anotherText by remember { mutableStateOf(searchText) }
    var text by remember { mutableStateOf("") }
    var textError by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    TopAppBar(
        modifier = Modifier,
        elevation = 2.dp,
        navigationIcon = {
            IconButton(onClick = { onBackClicked() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go back")
            }
        },
        title = {
            TextField(
                modifier = Modifier
                    .padding(2.dp)
                    .width(300.dp),
                value = searchText.value,
                onValueChange = {
                    textError = it.isNullOrBlank()
                    searchText.value = it },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy( imeAction = ImeAction.Search ),
                isError = textError,
                keyboardActions = KeyboardActions(onSearch = {
                    keyboardController?.hide()
                    onSearchClicked(searchText.value)
                }),
                trailingIcon = {
                    IconButton(onClick = { onSearchClicked(searchText.value) }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    } },
                shape = RoundedCornerShape(20.dp),
                textStyle = TextStyle(textAlign = TextAlign.Left, ),
                placeholder = { Text(text = "Type to search")}
                )
        },
    )
}

@Preview(showBackground = true)
@Composable
fun TopSearchBarPreview(){
    //TopSearchBar({}, {}, )
}