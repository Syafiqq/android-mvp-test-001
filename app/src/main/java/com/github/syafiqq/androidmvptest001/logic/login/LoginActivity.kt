package com.github.syafiqq.androidmvptest001.logic.login

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.syafiqq.androidmvptest001.R
import com.github.syafiqq.androidmvptest001.logic.home.HomeActivity
import com.github.syafiqq.androidmvptest001.model.entity.UserEntity
import com.github.syafiqq.ext.dagger.android.AndroidInjection
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_login.*
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginContract.View {
    @Inject
    lateinit var presenter: LoginContract.Presenter
    var disposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate [savedInstanceState: Bundle?]")

        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val s: Observer<View> = PublishSubject.create<View>().apply {
            throttleFirst(350, TimeUnit.MILLISECONDS)
            subscribe(::onLogin)?.addTo(disposable)
        }

        button.setOnClickListener(s::onNext)
        presenter.onCreate()
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

    override fun onLoginClick() {
        Timber.d("onLoginClick")

        button.isEnabled = false
    }

    override fun onInvalidEmail(cause: String) {
        Timber.d("onInvalidEmail [cause]")

        email.error = cause
        enableLoginButton()
    }

    override fun onInvalidPassword(cause: String) {
        Timber.d("onInvalidPassword [cause]")

        password.error = cause
        enableLoginButton()
    }

    override fun onSuccessLogin(user: UserEntity) {
        Timber.d("onSuccessLogin [user]")

        Timber.d("user $user")
        Toast.makeText(this, "Welcome ${user.name}", Toast.LENGTH_LONG).show()
    }

    override fun onFailedLogin(cause: String) {
        Timber.d("onFailedLogin [cause]")

        Toast.makeText(this, "cause", Toast.LENGTH_SHORT).show()
    }

    override fun onErrorLogin(ex: Throwable) {
        Timber.d("onErrorLogin [ex]")

        Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
    }

    override fun onCompleteLogin() {
        Timber.d("onCompleteLogin")

        enableLoginButton()
    }

    override fun onCancelLogin() {
        Timber.d("onCancelLogin")

        enableLoginButton()
    }

    override fun gotoHomeActivity() {
        Timber.d("gotoHomeActivity")

        startActivity(Intent(this, HomeActivity::class.java)).also {
            finish()
        }
    }

    private fun enableLoginButton() {
        button.isEnabled = true
    }

    private fun onLogin(view: View) {
        presenter.doLogin(email.editText?.text.toString(), password.editText?.text.toString())
    }
}
