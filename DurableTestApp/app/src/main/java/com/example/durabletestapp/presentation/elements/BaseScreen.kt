package com.example.durabletestapp.presentation.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    mainText: String,
    buttonText: String,
    showFinishButton: Boolean = false,
    onButtonClick: () -> Unit,
    onFinishClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            .then(modifier),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = mainText,
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        if (showFinishButton) {
            Button(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 24.dp, bottom = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                onClick = {
                    if (onFinishClick != null) {
                        onFinishClick()
                    }
                },
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = "FINISH",
                )
            }
        }

        Button(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)
                .padding(top = 24.dp, bottom = 24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            onClick = { onButtonClick() },
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = buttonText,
            )
        }
    }
}