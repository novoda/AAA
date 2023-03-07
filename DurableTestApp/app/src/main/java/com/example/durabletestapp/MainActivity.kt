package com.example.durabletestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.durabletestapp.presentation.ViewModel
import com.example.durabletestapp.presentation.ViewModel.UiState
import com.example.durabletestapp.presentation.elements.ContentScreen
import com.example.durabletestapp.presentation.elements.InitialScreen
import com.example.durabletestapp.presentation.elements.LoadingScreen
import com.example.durabletestapp.presentation.elements.WarningScreen
import com.example.durabletestapp.ui.theme.DurableTestAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewState by viewModel.state.collectAsState()
            DurableTestAppTheme() {
                viewState.Render(viewModel)
            }
        }
    }
}

@Composable
private fun UiState.Render(viewModel: ViewModel) = when (this) {
    is UiState.Initial -> InitialScreen() {
        viewModel.startThings()
    }
    is UiState.Error -> WarningScreen() {
        viewModel.startThings()
    }
    UiState.Loading -> LoadingScreen()
    is UiState.Ready -> ContentScreen(
        title = this.text,
        showFinishButton = this.showFinishButton,
        onButtonClick = { viewModel.reset() },
        onFinishClick = { viewModel.finish() }
    )
    is UiState.Final -> ContentScreen(
        title = this.text,
        showFinishButton = false,
        onButtonClick = { viewModel.reset() },
        onFinishClick = null
    )
}