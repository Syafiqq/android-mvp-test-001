package com.github.syafiqq.androidmvptest001.logic.user.home

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.github.syafiqq.androidmvptest001.R
import com.github.syafiqq.androidmvptest001.databinding.ActivityHomeBinding
import com.github.syafiqq.androidmvptest001.logic.user.detail.DetailActivity
import com.github.syafiqq.androidmvptest001.model.di.component.UserComponent
import com.github.syafiqq.ext.dagger.android.AndroidInjection
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_home.*
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeContract.View, LifecycleOwner {
    lateinit var binding: ActivityHomeBinding
    @Inject
    lateinit var presenter: HomeContract.Presenter
    private var disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate [savedInstanceState: Bundle?]")

        AndroidInjection.inject(this, UserComponent::class.java)
        lifecycle.addObserver(presenter.lifecycleObserver)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setSupportActionBar(toolbar)


        val s: Observer<View> = PublishSubject.create<View>().apply {
            throttleFirst(350, TimeUnit.MILLISECONDS)
            subscribe { presenter.onMailClicked() }?.addTo(disposable)
        }

        fab.setOnClickListener(s::onNext)
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
    }

    override fun onResume() {
        Timber.d("onResume")
        super.onResume()

        binding.presenter = presenter
    }

    override fun onRestart() {
        Timber.d("onRestart")
        super.onRestart()

        presenter.onRestart()
    }

    override fun onPause() {
        Timber.d("onPause")
        super.onPause()
    }

    override fun onStop() {
        Timber.d("onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Timber.d("onDestroy")
        disposable.dispose()

        super.onDestroy()
    }

    override fun gotoDetailActivity() {
        Timber.d("gotoDetailActivity")

        startActivity(Intent(this, DetailActivity::class.java))
    }

}
