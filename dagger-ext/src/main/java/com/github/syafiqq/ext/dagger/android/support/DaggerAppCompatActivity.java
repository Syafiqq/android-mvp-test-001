package com.github.syafiqq.ext.dagger.android.support;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.github.syafiqq.ext.dagger.android.AndroidInjection;
import com.github.syafiqq.ext.dagger.android.HasAndroidInjector;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.internal.Beta;

/**
 * An {@link AppCompatActivity} that injects its members in {@link #onCreate(Bundle)} and can be
 * used to inject {@code Fragment}s attached to it.
 */
@Beta
public abstract class DaggerAppCompatActivity extends AppCompatActivity
        implements HasAndroidInjector {

    @Inject DispatchingAndroidInjector<Object> androidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this, rootComponent());
        super.onCreate(savedInstanceState);
    }

    protected abstract Class<?> rootComponent();

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }

    @Override
    public AndroidInjector<Object> androidInjector(Class<?> cls) {
        return null;
    }

    @Override
    public void clear(Class<?> cls) {

    }

    @Override
    public boolean exists(Class<?> cls) {
        return false;
    }
}
