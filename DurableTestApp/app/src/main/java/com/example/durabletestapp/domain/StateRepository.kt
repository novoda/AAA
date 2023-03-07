package com.example.durabletestapp.domain

import com.example.durabletestapp.presentation.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StateRepository {
    private val _state = MutableStateFlow<ViewModel.UiState>(ViewModel.UiState.Initial)
    internal val state: StateFlow<ViewModel.UiState> = _state

    fun setState(state: ViewModel.UiState) {
        _state.value = state
    }
}