package com.github.syafiqq.androidmvptest001.logic.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.github.syafiqq.androidmvptest001.R
import com.github.syafiqq.androidmvptest001.logic.login.LoginActivity
import com.github.syafiqq.ext.dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_splash_screen.*
import timber.log.Timber
import javax.inject.Inject

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashScreenActivity : AppCompatActivity(), SplashScreenContract.View, LifecycleOwner {
    @Inject
    lateinit var presenter: SplashScreenContract.Presenter

    private val mHideHandler = Handler()
    private val mHidePart2Runnable = Runnable {
        // Delayed removal of status and navigation bar

        // Note that some of these constants are new as of API 16 (Jelly Bean)
        // and API 19 (KitKat). It is safe to use them, as they are inlined
        // at compile-time and do nothing on earlier devices.
        fullscreen_content.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }
    private var mVisible: Boolean = false
    private val mHideRunnable = Runnable(::hide)

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate [savedInstanceState: Bundle?]")

        AndroidInjection.inject(this)
        lifecycle.addObserver(presenter.lifecycleObserver)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash_screen)

        mVisible = true
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        Timber.d("onCreate")
        super.onCreate(savedInstanceState, persistentState)

        presenter.onCreateWithPersistence()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        Timber.d("onPostCreate [savedInstanceState: Bundle?]")
        super.onPostCreate(savedInstanceState)

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100)
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

        super.onDestroy()
    }

    override fun gotoLoginActivity() {
        Timber.d("gotoLoginActivity")

        startActivity(Intent(this, LoginActivity::class.java)).also {
            finish()
        }
    }

    private fun hide() {
        Timber.d("hide")
        // Hide UI first
        supportActionBar?.hide()
        mVisible = false

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    /**
     * Schedules a call to hide() in [delayMillis], canceling any
     * previously scheduled calls.
     */
    private fun delayedHide(delayMillis: Int) {
        Timber.d("delayedHide [delayMillis: Int]")

        mHideHandler.removeCallbacks(mHideRunnable)
        mHideHandler.postDelayed(mHideRunnable, delayMillis.toLong())
    }

    companion object {
        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private const val UI_ANIMATION_DELAY = 300
    }
}
