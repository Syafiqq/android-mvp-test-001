package com.github.syafiqq.ext.dagger.android.support;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.github.syafiqq.ext.dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;

import com.github.syafiqq.ext.dagger.android.DaggerApplication;
import com.github.syafiqq.ext.dagger.android.HasAndroidInjector;
import dagger.internal.Beta;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * An {@link AppCompatActivity} that injects its members in {@link #onCreate(Bundle)} and can be
 * used to inject {@code Fragment}s attached to it.
 */
@Beta
public abstract class DaggerAppCompatActivity extends AppCompatActivity
    implements HasAndroidInjector {
  private Map<Class<?>, Object> holders = new HashMap<>();
  private Map<Class<?>, DispatchingAndroidInjector<Object>> injectors = new HashMap<>();

  @Inject DispatchingAndroidInjector<Object> androidInjector;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    injectIfNecessary(rootComponent());
    super.onCreate(savedInstanceState);
  }

  /**
   * Implementations should return an {@link AndroidInjector} for the concrete {@link
   * DaggerApplication}. Typically, that injector is a {@link dagger.Component}.
   */

  protected AndroidInjector<?> applicationInjector(Class<?> cls, Map<Class<?>, Object> holders) {
    return null;
  }

  protected abstract Class<?> rootComponent();

  private void injectIfNecessary(Class<?> cls) {
    if (!injectors.containsKey(cls) || injectors.get(cls) == null) {
      synchronized (this) {
        if (!injectors.containsKey(cls) || injectors.get(cls) == null) {
          if(cls.equals(rootComponent())) {
            AndroidInjection.inject(this, cls);
          }
          else {
            if (!holders.containsKey(cls))
              holders.put(cls, applicationInjector(cls, holders));
            @SuppressWarnings("unchecked")
            AndroidInjector<DaggerAppCompatActivity> applicationInjector =
                    (AndroidInjector<DaggerAppCompatActivity>) holders.get(cls);
            if (applicationInjector == null) {
              throw new IllegalStateException(
                      "The AndroidInjector returned from applicationInjector() returns null ");
            }
            applicationInjector.inject(this);
          }
          if (androidInjector == null) {
            throw new IllegalStateException(
                    "The AndroidInjector returned from applicationInjector() did not inject the "
                            + "DaggerApplication");
          }
          injectors.put(cls, androidInjector);
          return;
        }
      }
    }
    synchronized (this) {
      androidInjector = injectors.get(cls);
    }
  }

  @Override
  public AndroidInjector<Object> androidInjector(Class<?> cls) {
    // injectIfNecessary should already be called unless we are about to inject a ContentProvider,
    // which can happen before Application.onCreate()
    injectIfNecessary(cls);

    return androidInjector;
  }

  @Override
  public AndroidInjector<Object> androidInjector() {
    return androidInjector(rootComponent());
  }

  @Override
  public void clear(Class<?> cls) {
    holders.remove(cls);
    injectors.remove(cls);
  }

  @Override
  public boolean exists(Class<?> cls) {
    return injectors.containsKey(cls);
  }
}
