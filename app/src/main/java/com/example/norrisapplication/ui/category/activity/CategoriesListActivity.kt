package com.example.norrisapplication.ui.category.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.norrisapplication.NorrisApplication
import com.example.norrisapplication.R
import com.example.norrisapplication.di.component.ApiComponent
import com.example.norrisapplication.helper.extensions.hide
import com.example.norrisapplication.helper.extensions.show
import com.example.norrisapplication.ui.category.adapter.CategoryAdapter
import com.example.norrisapplication.ui.category.adapter.CategoryAdapterListener
import com.example.norrisapplication.ui.category.contract.CategoryContract
import com.example.norrisapplication.ui.category.presenter.CategoryPresenter
import com.example.norrisapplication.ui.joke.activity.JokeActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_categories_list.*
import javax.inject.Inject


class CategoriesListActivity : AppCompatActivity(),
    CategoryAdapterListener, CategoryContract.View {

    @Inject
    override lateinit var presenter: CategoryPresenter

    private val apiComponent: ApiComponent
        get() {
            val application = application as NorrisApplication
            return application.apiComponent ?: throw IllegalStateException()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_list)

        apiComponent.inject(categoriesListActivity = this)

        startActivity()
    }

    override fun onDestroy() {
        super.onDestroy()
        dispose()
    }

    override fun onItemClicked(categoryName: String) {
        startActivity(JokeActivity.createIntent(context = this, categoryName = categoryName))
    }

    override fun displayErrorMessage() {
        Snackbar.make(progressBar, getString(R.string.get_categories_error), Snackbar.LENGTH_LONG)
            .show()
    }

    override fun displayListWithCategories(categoriesList: List<String>) {
        val categoryAdapter = CategoryAdapter(
            categoriesList = categoriesList,
            categoryAdapterListener = this
        )

        setupRecyclerView(categoryAdapter = categoryAdapter)
    }

    private fun setupRecyclerView(categoryAdapter: CategoryAdapter) {
        with(categoriesRecycler) {
            layoutManager = LinearLayoutManager(this@CategoriesListActivity)
            adapter = categoryAdapter
            show()
        }
    }

    override fun startActivity() {
        with(presenter) {
            attachView(view = this@CategoriesListActivity)
            start()
        }

    }

    override fun hideLoading() {
        progressBar.hide()
    }

    override fun showLoading() {
        progressBar.show()
    }

    override fun dispose() {
        presenter.compositeDisposable.dispose()
    }

}
