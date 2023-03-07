package com.example.durabletestapp

import android.app.Application
import com.example.durabletestapp.di.apiModule
import com.example.durabletestapp.di.networkModule
import com.example.durabletestapp.di.repositoryModule
import com.example.durabletestapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(
                listOf(
                    networkModule,
                    viewModelModule,
                    apiModule,
                    repositoryModule)
            )
        }
    }
}