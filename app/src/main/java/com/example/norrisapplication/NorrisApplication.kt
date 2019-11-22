package com.example.norrisapplication

import android.app.Application
import com.example.norrisapplication.di.component.ApiComponent
import com.example.norrisapplication.di.component.DaggerApiComponent

class NorrisApplication : Application() {

    var apiComponent: ApiComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        apiComponent = DaggerApiComponent
            .builder()
            .build()
    }
}