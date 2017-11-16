/*
 *
 *  Copyright(c) 2017 VerstSiu
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.ijoic.lazy_fragment

import android.app.Fragment
import android.os.Bundle

/**
 * Lazy fragment.
 *
 * @author VerstSiu 2017/11/16.
 * @version 1.0
 */
abstract class LazyFragment: Fragment(), LazyState.Companion.Callback {
  protected val lazyState = LazyState()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    lazyState.callback = this
  }

  override fun onDestroy() {
    super.onDestroy()
    lazyState.callback = null
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    lazyState.onActivityCreated(savedInstanceState)
  }

  override fun onResume() {
    super.onResume()
    lazyState.onResume()
  }

  override fun onPause() {
    super.onPause()
    lazyState.onPause()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    lazyState.onDestroyView()
  }

  override fun setUserVisibleHint(isVisibleToUser: Boolean) {
    super.setUserVisibleHint(isVisibleToUser)
    lazyState.setUserVisibleHint(isVisibleToUser)
  }

}