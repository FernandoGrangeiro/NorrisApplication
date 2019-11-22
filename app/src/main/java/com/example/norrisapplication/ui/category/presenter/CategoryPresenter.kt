package com.example.norrisapplication.ui.category.presenter

import com.example.norrisapplication.api.ApiRepository
import com.example.norrisapplication.ui.category.contract.CategoryContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class CategoryPresenter @Inject constructor() : CategoryContract.Presenter {

    override val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private lateinit var view: CategoryContract.View

    @Inject
    lateinit var apiRepository: ApiRepository

    fun attachView(view: CategoryContract.View) {
        this.view = view
    }

    override fun start() {

        val disposable = apiRepository.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { success, error ->
                    view.hideLoading()

                    success?.let { response ->
                        view.displayListWithCategories(response)
                    }

                    error?.let {
                        view.displayErrorMessage()
                    }

                }
        compositeDisposable.add(disposable)
    }
}