package com.example.norrisapplication.ui.joke.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.norrisapplication.NorrisApplication
import com.example.norrisapplication.R
import com.example.norrisapplication.di.component.ApiComponent
import com.example.norrisapplication.entities.Joke
import com.example.norrisapplication.helper.extensions.hide
import com.example.norrisapplication.helper.extensions.show
import com.example.norrisapplication.ui.joke.contract.JokeContract
import com.example.norrisapplication.ui.joke.presenter.JokePresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_joke.*
import javax.inject.Inject


class JokeActivity : AppCompatActivity(), JokeContract.View {

    @Inject
    override lateinit var presenter: JokePresenter


    private val apiComponent: ApiComponent
        get() {
            val application = application as NorrisApplication
            return application.apiComponent ?: throw IllegalStateException()
        }

    companion object {
        private const val CATEGORY_NAME = "CATEGORY_NAME"

        fun createIntent(context: Context, categoryName: String): Intent {
            return Intent(context, JokeActivity::class.java)
                .putExtra(CATEGORY_NAME, categoryName)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke)

        apiComponent.inject(this)

        startActivity()
    }

    override fun onDestroy() {
        super.onDestroy()
        dispose()
    }

    override fun displayErrorMessage() {
        Snackbar.make(progressBar, getString(R.string.get_joke_error), Snackbar.LENGTH_LONG)
            .show()
    }

    override fun displayJoke(joke: Joke) {
        setJokeLink(joke.url)
        setJokeText(joke.value)
        loadImage(imageUrl = joke.iconUrl)
    }

    private fun setJokeLink(link: String) {
        with(jokeLink) {
            text = createClickableText()

            setOnClickListener {
                openUrl(link)
            }
        }
    }

    private fun createClickableText(): SpannableString {

        return SpannableString(getString(R.string.url_link)).apply {
            setSpan(UnderlineSpan(), 0, this.length, 0)
        }
    }

    private fun openUrl(url: String) {
        val openURL = Intent(Intent.ACTION_VIEW).apply {
            this.data = Uri.parse(url)
        }

        startActivity(openURL)
    }

    private fun setJokeText(text: String) {
        jokeText.text = text
    }

    private fun loadImage(imageUrl: String) {
        Glide
            .with(this)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(jokeImage)
    }

    override fun startActivity() {
        with(presenter) {
            attachView(view = this@JokeActivity)
            getJokeByCategory(categoryName = intent.getStringExtra(CATEGORY_NAME) ?: "")
        }

    }


    override fun hideLoading() {
        progressBar.hide()
        contentConstraintLayout.show()
    }

    override fun showLoading() {
        progressBar.show()
    }

    override fun dispose() {
        presenter.compositeDisposable.dispose()
    }
}
