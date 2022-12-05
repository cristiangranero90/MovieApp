package com.progressapp.movieapp.composable.moviescreen.components

import android.util.Log
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun SeeMoreText(
    text: String
){
    if (text.isBlank()) {
        Text(
            text = "No more information...",
            fontSize = 18.sp
        )
    }
    else {
        //From android.developer
        val annotatedText = buildAnnotatedString {
            pushStringAnnotation("URL", text)
            withStyle(style = SpanStyle(color = Color.Blue)){
                append("Home - WEBSITE")
            }
            pop()
        }

        val uriHandler = LocalUriHandler.current

        ClickableText(
            text = annotatedText,
            onClick = { offset ->
                // We check if there is an *URL* annotation attached to the text
                // at the clicked position
                annotatedText.getStringAnnotations(tag = "URL", start = offset,
                    end = offset)
                    .firstOrNull()?.let {
                            annotation ->
                        uriHandler.openUri(annotation.item)
                        Log.d("Clicked URL", annotation.item)
                    }
            }
        )
    }
}