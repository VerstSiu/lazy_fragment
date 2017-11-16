package com.ijoic.lazy_fragment.support

import android.os.Bundle
import android.support.v4.app.Fragment
import com.ijoic.lazy_fragment.LazyState

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