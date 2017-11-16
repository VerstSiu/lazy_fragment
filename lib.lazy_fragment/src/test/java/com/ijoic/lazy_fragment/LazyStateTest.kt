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
import org.junit.Test
import org.mockito.Mockito.*

/**
 * Lazy state test.
 *
 * @author VerstSiu 2017/11/15.
 * @version 1.0
 */
class LazyStateTest {

  /* Cases for single operation. */

  @Test
  fun testSingleCreate() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    val timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testSingleVisibleTrue() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    val timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  /* Cases for couple operation. */

  @Test
  fun testCoupleResumeVisibleTrue() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    val timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCouplePauseVisibleTrue() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    val timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.onPause()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleVisibleTrueCreate() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleVisibleTrueResume() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    val timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleVisibleTruePause() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    val timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onPause()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  /* Cases for triple operation. */

  @Test
  fun testTripleCreateCreateVisibleTrue() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testTripleCreateResumeVisibleTrue() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testTripleCreatePauseVisibleTrue() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onPause()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testTripleCreateDestroyVisibleTrue() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    val timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onDestroyView()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testTripleCreateVisibleTrueResume() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testTripleCreateVisibleTruePause() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onPause()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testTripleResumeCreateVisibleTrue() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testTripleResumeVisibleTrueCreate() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testTriplePauseCreateVisibleTrue() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.onPause()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testTriplePauseVisibleTrueCreate() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.onPause()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testTripleVisibleTrueCreateCreate() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testTripleVisibleTrueCreateResume() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    var timesResume = 0

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testTripleVisibleTrueCreatePause() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onPause()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testTripleVisibleTrueResumeCreate() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testTripleVisibleTrueResumeDestroy() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    val timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onDestroyView()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testTripleVisibleTruePauseCreate() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onPause()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testTripleVisibleTrueToFalseCreate() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    val timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(false)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testTripleVisibleTrueToFalseDestroy() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    val timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(false)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onDestroyView()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  /* Cases for four pair operation. */

  @Test
  fun testCoupleFourCreateResumeCreateVisibleTrue() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFourCreateResumeResumeVisibleTrue() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFourCreateResumePauseVisibleTrue() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onPause()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFourCreateResumeDestroyVisibleTrue() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    val timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onDestroyView()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFourCreateResumeVisibleTrueCreate() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFourCreateResumeVisibleTrueResume() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFourCreateResumeVisibleTruePause() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    var timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onPause()
    ++timesPause
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFourCreateResumeVisibleTrueToFalse() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    var timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(false)
    ++timesPause
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFourCreatePauseResumeVisibleTrue() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onPause()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFourCreateDestroyCreateVisibleTrue() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onDestroyView()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFourCreateDestroyResumeVisibleTrue() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    val timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onDestroyView()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFourCreateVisibleTrueResumeResume() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFourCreateVisibleTrueResumePause() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    var timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onPause()
    ++timesPause
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFourCreateVisibleTrueResumeVisibleFalse() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    var timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(false)
    ++timesPause
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFourCreateVisibleTrueDestroyCreate() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onDestroyView()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFourCreateVisibleTrueDestroyResume() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onDestroyView()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFourCreateVisibleTrueToFalseResume() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(false)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFourVisibleTrueCreateResumePause() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    var timesPause = 0
    var timesResume = 0

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onPause()
    ++timesPause
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFourVisibleTrueCreateDestroyCreate() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onDestroyView()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFourVisibleTrueCreateVisibleTrueResume() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    var timesResume = 0

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFourVisibleTrueCreateVisibleFalseResume() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(false)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  /* Cases for five pair operation. */

  @Test
  fun testCoupleFiveCreateResumePauseVisibleTrueResume() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onPause()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFiveCreateResumePauseVisibleTrueToFalse() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onPause()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(false)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFiveCreateResumeVisibleTruePauseResume() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    var timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onPause()
    ++timesPause
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFiveCreateResumeVisibleTruePauseVisibleFalse() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    var timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onPause()
    ++timesPause
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(false)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFiveCreateResumeVisibleTrueToFalsePause() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    var timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(false)
    ++timesPause
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onPause()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFiveCreateResumeVisibleTrueToFalseToTrue() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    var timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(false)
    ++timesPause
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFiveCreateVisibleTrueResumePauseResume() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    var timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onPause()
    ++timesPause
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFiveCreateVisibleTrueResumePauseVisibleFalse() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    var timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onPause()
    ++timesPause
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(false)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFiveCreateVisibleTrueResumeDestroyCreate() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onDestroyView()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFiveCreateVisibleTrueResumeVisibleFalsePause() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    var timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(false)
    ++timesPause
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onPause()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFiveCreateVisibleTrueResumeVisibleFalseToTrue() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    var timesPause = 0
    var timesResume = 0

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(false)
    ++timesPause
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFiveVisibleTrueCreateDestroyCreateResume() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    var timesResume = 0

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onDestroyView()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFiveVisibleTrueCreateDestroyVisibleFalseCreate() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onDestroyView()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(false)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFiveVisibleTrueCreateVisibleFalseResumePause() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    val timesResume = 0

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(false)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onPause()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }

  @Test
  fun testCoupleFiveVisibleTrueCreateVisibleFalseResumeVisibleTrue() {
    val savedState = Bundle()
    val lazyCallback = mock(LazyState.Companion.Callback::class.java)
    val lazyState = LazyState(lazyCallback)

    var timesCreate = 0
    val timesPause = 0
    var timesResume = 0

    lazyState.setUserVisibleHint(true)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onActivityCreated(savedState)
    ++timesCreate
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(false)
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.onResume()
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()

    lazyState.setUserVisibleHint(true)
    ++timesResume
    verify(lazyCallback, times(timesCreate)).onLazyCreate(savedState)
    verify(lazyCallback, times(timesPause)).onLazyPause()
    verify(lazyCallback, times(timesResume)).onLazyResume()
  }
}