package com.example.durabletestapp.domain

import com.example.durabletestapp.data.Api
import kotlinx.coroutines.coroutineScope

class Repository(private val api: Api) {

    suspend fun getThing(regId: String) = coroutineScope {
        api.getThing(regId)
    }

    suspend fun finish(orchestrationId: String) = coroutineScope {
        api.finish(orchestrationId)
    }
}