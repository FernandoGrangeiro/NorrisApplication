package com.example.norrisapplication.ui.base.presenter

import io.reactivex.disposables.CompositeDisposable

interface BasePresenter {
    val compositeDisposable: CompositeDisposable
}