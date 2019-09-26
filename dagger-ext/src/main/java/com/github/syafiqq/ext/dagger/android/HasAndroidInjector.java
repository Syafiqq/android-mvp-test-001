/*
 * Copyright (C) 2016 The Dagger Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.syafiqq.ext.dagger.android;

import dagger.android.AndroidInjector;
import dagger.internal.Beta;

/** Provides an {@link AndroidInjector}. */
@Beta
public interface HasAndroidInjector extends dagger.android.HasAndroidInjector {
    /** Returns an {@link AndroidInjector}. */
    AndroidInjector<Object> androidInjector(Class<?> cls);

    void clear(Class<?> cls);

    boolean exists(Class<?> cls);
}
