package com.example.marvelcharacterapp

import android.app.Application
import com.example.marvelcharacterapp.di.networkModule
import com.example.marvelcharacterapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MarvelApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Android context
            androidContext(this@MarvelApplication)
            androidLogger()
            // modules
            modules(networkModule + viewModelModule)
        }
        instance = this
    }

    companion object {
        var instance: MarvelApplication? = null
    }
}