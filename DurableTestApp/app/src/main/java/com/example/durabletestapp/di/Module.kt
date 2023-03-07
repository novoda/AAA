package com.example.durabletestapp.di

import com.example.durabletestapp.data.Api
import com.example.durabletestapp.domain.Repository
import com.example.durabletestapp.domain.StateRepository
import com.example.durabletestapp.presentation.ViewModel
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:7071/api/")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideHttpClient() }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }
}

val viewModelModule = module {
    viewModel {
        ViewModel(
            repository = get(),
            stateRepository = get(),
            fcm = FirebaseMessaging.getInstance()
        )
    }
}

val apiModule = module {
    fun provideAlbumsApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    single { provideAlbumsApi(get()) }
}



val repositoryModule = module {
    fun provideRepository(api: Api): Repository {
        return Repository(api)
    }

    single { provideRepository(get()) }

    fun provideStateRepository(): StateRepository {
        return StateRepository()
    }

    single { provideStateRepository() }
}