package com.example.durabletestapp.presentation.elements

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    title: String,
    showFinishButton: Boolean = false,
    onButtonClick: () -> Unit,
    onFinishClick: (() -> Unit)? = null
) {
    BaseScreen(
        modifier = modifier,
        mainText = title,
        buttonText = "Reset",
        showFinishButton = showFinishButton,
        onFinishClick = onFinishClick,
        onButtonClick = onButtonClick
    )
}