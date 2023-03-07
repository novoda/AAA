package com.example.durabletestapp.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @GET("orchestrators/{registrationId}")
    suspend fun getThing(@Path("registrationId") regId: String): Response<ApiResponse>

    @GET("Approve/{orchestrationId}")
    suspend fun finish(@Path("orchestrationId") orchestrationId: String)
}