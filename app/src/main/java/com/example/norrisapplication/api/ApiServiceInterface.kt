package com.example.norrisapplication.api

import com.example.norrisapplication.entities.Joke
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceInterface {

    @GET("jokes/random?")
    fun getJoke(@Query("category") category: String?): Single<Joke>

    @GET("jokes/categories")
    fun getCategories(): Single<List<String>>

}