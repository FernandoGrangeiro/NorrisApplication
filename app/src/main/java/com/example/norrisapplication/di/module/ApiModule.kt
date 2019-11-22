package com.example.norrisapplication.di.module

import com.example.norrisapplication.api.ApiServiceInterface
import com.example.norrisapplication.helper.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiServiceInterface {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()

        return retrofit.create(ApiServiceInterface::class.java)
    }
}