package com.example.norrisapplication.ui.base.activity

interface BaseView<C> {
    var presenter: C
    fun hideLoading()
    fun showLoading()
    fun dispose()

}