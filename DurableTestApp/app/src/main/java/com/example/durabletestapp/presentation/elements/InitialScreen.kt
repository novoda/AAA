package com.example.durabletestapp.presentation.elements

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun InitialScreen(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
) {
    BaseScreen(
        modifier = modifier,
        mainText = "Click the button to do something",
        buttonText = "Do Something",
        onButtonClick = onButtonClick
    )
}