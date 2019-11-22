package com.example.norrisapplication.ui.joke.contract

import com.example.norrisapplication.entities.Joke
import com.example.norrisapplication.ui.base.activity.BaseView
import com.example.norrisapplication.ui.base.presenter.BasePresenter
import com.example.norrisapplication.ui.joke.presenter.JokePresenter

interface JokeContract {

    interface View : BaseView<JokePresenter> {
        fun startActivity()
        fun displayErrorMessage()
        fun displayJoke(joke: Joke)
    }

    interface Presenter : BasePresenter{
        fun getJokeByCategory(categoryName: String)

    }
}