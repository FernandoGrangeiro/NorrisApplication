package com.example.norrisapplication.ui.category.contract

import com.example.norrisapplication.ui.base.activity.BaseView
import com.example.norrisapplication.ui.base.presenter.BasePresenter
import com.example.norrisapplication.ui.category.presenter.CategoryPresenter

interface CategoryContract {

    interface View : BaseView<CategoryPresenter> {
        fun displayErrorMessage()
        fun displayListWithCategories(categoriesList: List<String>)
        fun startActivity()

    }

    interface Presenter : BasePresenter {
        fun start()
    }
}