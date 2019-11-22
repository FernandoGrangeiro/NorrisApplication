package com.example.norrisapplication.ui.joke.presenter

import com.example.norrisapplication.api.ApiRepository
import com.example.norrisapplication.ui.joke.contract.JokeContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class JokePresenter @Inject constructor() : JokeContract.Presenter {

    private lateinit var view: JokeContract.View

    @Inject
    lateinit var apiRepository: ApiRepository

    override val compositeDisposable: CompositeDisposable = CompositeDisposable()


    fun attachView(view: JokeContract.View) {
        this.view = view
    }

    override fun getJokeByCategory(categoryName: String) {

        val disposable = apiRepository.getJoke(categoryName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { success, error ->
                view.hideLoading()

                success?.let { response ->
                    view.displayJoke(response)
                }

                error?.let {
                    view.displayErrorMessage()
                }

            }

        compositeDisposable.add(disposable)
    }

}