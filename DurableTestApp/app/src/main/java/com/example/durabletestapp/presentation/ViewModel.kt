package com.example.durabletestapp.presentation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.durabletestapp.domain.Repository
import com.example.durabletestapp.domain.StateRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class ViewModel(
    private val repository: Repository,
    private val stateRepository: StateRepository,
    fcm: FirebaseMessaging
) : ViewModel() {
    //   private val viewStateSource = MutableStateFlow<UiState>(UiState.Initial)
    internal val state: StateFlow<UiState> = stateRepository.state

    private lateinit var fcmToken: String

    private var orchestrationId: String? = ""


    init {
        fcm.token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            fcmToken = token
            // Log and toast
            val msg = "FCM Token: $token"
            Log.d(TAG, msg)
        })
    }

    private suspend fun getThings() {
        fcmToken.let {
            kotlin.runCatching { repository.getThing(it) }.fold(onSuccess = {
                if (it.isSuccessful){
                   orchestrationId = it.body()?.id
                }
                stateRepository.setState(UiState.Ready("Initial stage"))
                // viewStateSource.value = UiState.Ready

            }, onFailure = {
                stateRepository.setState(UiState.Error(networkError = true))
                // viewStateSource.value = UiState.Error(networkError = true)
            })
        }
    }

    fun finish() {
        viewModelScope.launch {
            orchestrationId?.let {
                endFlow(it)
            }
        }
    }

    private suspend fun endFlow(orchestrationId: String) {
        kotlin.run { repository.finish(orchestrationId) }
    }

    fun startThings() {
        stateRepository.setState(UiState.Loading)
        //viewStateSource.value = UiState.Loading
        viewModelScope.launch { getThings() }
    }

    fun reset() {
        stateRepository.setState(UiState.Initial)
    }


    sealed class UiState {
        object Initial : UiState()
        object Loading : UiState()
        data class Error(val networkError: Boolean) : UiState()
        data class Ready(val text: String, val showFinishButton: Boolean = false) : UiState()
        data class Final(val text: String) : UiState()
    }
}