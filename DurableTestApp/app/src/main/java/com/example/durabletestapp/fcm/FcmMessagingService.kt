package com.example.durabletestapp.fcm

import com.example.durabletestapp.domain.StateRepository
import com.example.durabletestapp.presentation.ViewModel
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.koin.android.ext.android.inject

class FcmMessagingService : FirebaseMessagingService() {

    private val stateRepository by inject<StateRepository>()

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        message.data["stage"]?.let {
            when (it) {
                "last" -> {
                    stateRepository.setState(ViewModel.UiState.Final("This is the end of the line!"))
                }
                "waiting" -> {
                    stateRepository.setState(ViewModel.UiState.Ready(it, true))
                }
                else -> {
                    stateRepository.setState(ViewModel.UiState.Ready(it))
                }
            }
        }

        super.onMessageReceived(message)
    }
}