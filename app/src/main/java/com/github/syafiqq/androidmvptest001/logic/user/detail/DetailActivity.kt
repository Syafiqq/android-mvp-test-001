package com.github.syafiqq.androidmvptest001.logic.user.detail

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.github.syafiqq.androidmvptest001.R
import com.github.syafiqq.androidmvptest001.model.di.component.UserComponent
import com.github.syafiqq.ext.dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable

import kotlinx.android.synthetic.main.activity_detail.*
import timber.log.Timber
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), DetailContract.View {
    @Inject
    lateinit var presenter: DetailContract.Presenter
    private var disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate [savedInstanceState: Bundle?]")

        AndroidInjection.inject(this, UserComponent::class.java)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        Timber.d("onCreate")
        super.onCreate(savedInstanceState, persistentState)

        presenter.onCreateWithPersistence()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        Timber.d("onPostCreate [savedInstanceState: Bundle?]")
        super.onPostCreate(savedInstanceState)

        presenter.onPostCreate()
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        Timber.d("onPostCreate [savedInstanceState: Bundle?, persistentState: PersistableBundle?]")
        super.onPostCreate(savedInstanceState, persistentState)

        presenter.onPostCreateWithPersistence()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Timber.d("onSaveInstanceState [outState: Bundle]")
        super.onSaveInstanceState(outState)

        presenter.onSaveInstanceState()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        Timber.d("onSaveInstanceState [outState: Bundle, outPersistentState: PersistableBundle]")
        super.onSaveInstanceState(outState, outPersistentState)

        presenter.onSaveInstanceStateWithPersistence()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Timber.d("onRestoreInstanceState [savedInstanceState: Bundle]")
        super.onRestoreInstanceState(savedInstanceState)

        presenter.onRestoreInstanceState()
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        Timber.d("onRestoreInstanceState [savedInstanceState: Bundle?, persistentState: PersistableBundle?]")
        super.onRestoreInstanceState(savedInstanceState, persistentState)

        presenter.onRestoreInstanceStateWithPersistence()
    }

    override fun onStart() {
        Timber.d("onStart")
        super.onStart()

        presenter.onStart()
    }

    override fun onResume() {
        Timber.d("onResume")
        super.onResume()

        presenter.onResume()
    }

    override fun onRestart() {
        Timber.d("onRestart")
        super.onRestart()

        presenter.onRestart()
    }

    override fun onPause() {
        Timber.d("onPause")
        super.onPause()

        presenter.onPause()
    }

    override fun onStop() {
        Timber.d("onStop")
        presenter.onStop()

        super.onStop()
    }

    override fun onDestroy() {
        Timber.d("onDestroy")
        presenter.onDestroy()
        disposable.dispose()

        super.onDestroy()
    }
}
