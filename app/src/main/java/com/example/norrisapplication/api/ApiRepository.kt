package com.example.norrisapplication.api

import javax.inject.Inject

class ApiRepository @Inject constructor(private val api: ApiServiceInterface) {

    fun getCategories() = api.getCategories()


    fun getJoke(categoryName: String) = api.getJoke(category = categoryName)
}