package com.example.norrisapplication.di.component

import com.example.norrisapplication.di.module.ApiModule
import com.example.norrisapplication.ui.category.activity.CategoriesListActivity
import com.example.norrisapplication.ui.joke.activity.JokeActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApiModule::class])
@Singleton
interface ApiComponent {

    fun inject(categoriesListActivity: CategoriesListActivity)
    fun inject(jokeActivity: JokeActivity)
}

