package com.github.syafiqq.ext.dagger.android;

import dagger.internal.Beta;
import dagger.android.AndroidInjector;

/** Provides an {@link AndroidInjector}. */
@Beta
public interface HasAndroidInjector extends dagger.android.HasAndroidInjector {
  /** Returns an {@link AndroidInjector}. */
  AndroidInjector<Object> androidInjector(Class<?> cls);

  void clear(Class<?> cls);

  boolean exists(Class<?> cls);
}
