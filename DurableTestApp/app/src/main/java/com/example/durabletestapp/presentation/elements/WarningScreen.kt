package com.example.durabletestapp.presentation.elements

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WarningScreen(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
) {
    BaseScreen(
        modifier = modifier,
        mainText = "Something went wrong",
        buttonText = "Retry",
        onButtonClick = onButtonClick
    )
}