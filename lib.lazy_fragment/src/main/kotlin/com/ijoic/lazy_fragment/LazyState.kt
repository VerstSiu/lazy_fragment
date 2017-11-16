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

import android.os.Bundle
import android.util.Log

/**
 * Lazy state.
 *
 * <p>Force rules:</p>
 * <p>1. lazyResume, lazyPause could only be called when a fragment is created and not view destroyed.</p>
 * <p>2. lazyPause must be called after at least once lazyResume has been called when a fragment is created.</p>
 * <p>3. pause, resume state will be cleared if a new created was called.</p>
 * <p>4. lazyCreated could only be called once between a normal create, destroy lifecycle.</p>
 *
 * @author VerstSiu 2017/11/15.
 * @version 1.0
 */
class LazyState {
  private var userVisible = false
  private var cachedSavedInstanceState: Bundle? = null

  private var stateCreate = false
  private var stateCreateInit = false
  private var stateResume = false
  private var stateResumeInit = false

  constructor() {
  }

  constructor(callback: Callback) {
    this.callback = callback
  }

  /**
   * Callback.
   */
  var callback: Callback? = null

  /**
   * Activity created.
   */
  fun onActivityCreated(savedInstanceState: Bundle?) {
    val oldCreate = stateCreate
    cachedSavedInstanceState = savedInstanceState

    if (!oldCreate) {
      stateCreate = true
      stateCreateInit = false
      stateResume = false
      stateResumeInit = false

      if (userVisible) {
        stateCreateInit = true
        printLog("created")
        callback?.onLazyCreate(savedInstanceState)
      }
    }
  }

  /**
   * Pause.
   */
  fun onPause() {
    val oldResumeInit = stateResumeInit
    stateResume = false
    stateResumeInit = false

    if (stateCreate && oldResumeInit && userVisible) {
      printLog("pause")
      callback?.onLazyPause()
    }
  }

  /**
   * Resume.
   */
  fun onResume() {
    stateResume = true

    if (stateCreate && !stateResumeInit && userVisible) {
      stateResumeInit = true
      printLog("resume")
      callback?.onLazyResume()
    }
  }

  /**
   * User visible change.
   */
  fun setUserVisibleHint(isVisibleToUser: Boolean) {
    val oldVisibleToUser = userVisible
    userVisible = isVisibleToUser

    if (!stateCreate || oldVisibleToUser == isVisibleToUser) {
      return
    }
    if (isVisibleToUser) {
      onShiftVisibleDisplay()
    } else {
      onShiftVisibleHide()
    }
  }

  private fun onShiftVisibleDisplay() {
    if (!stateCreateInit) {
      stateCreateInit = true
      printLog("created")
      callback?.onLazyCreate(cachedSavedInstanceState)
    }
    if (stateResume && !stateResumeInit) {
      stateResumeInit = true
      printLog("resume")
      callback?.onLazyResume()
    }
  }

  private fun onShiftVisibleHide() {
    if (stateResume && stateResumeInit) {
      stateResumeInit = false
      printLog("pause")
      callback?.onLazyPause()
    }
  }

  /**
   * Destroy view.
   */
  fun onDestroyView() {
    stateCreate = false
  }

  /**
   * Created state.
   *
   * <p>Returns false if created is not called or destroy is called before this method.</p>
   */
  val isCreated: Boolean
      get() = stateCreate

  /**
   * Resume state.
   *
   * <p>Returns true if created is called and resume is called at least once, and pause is not called
   * after last resume.</p>
   */
  val isResume: Boolean
      get() = stateResume

  /* Log */

  /**
   * Log enabled.
   *
   * <p>Print lazy state called on LogCat as verbose level, if log mode enabled. Default as false.</p>
   */
  var logEnabled: Boolean = false

  private fun printLog(state: String) {
    if (logEnabled) {
      Log.v("lazy state", "[$this] lazy $state, callback - $callback")
    }
  }

  companion object {
    /**
     * Lazy state callback.
     */
    interface Callback {
      /**
       * Lazy create.
       */
      fun onLazyCreate(savedInstanceState: Bundle?)

      /**
       * Lazy pause.
       */
      fun onLazyPause()

      /**
       * Lazy resume.
       */
      fun onLazyResume()
    }
  }
}