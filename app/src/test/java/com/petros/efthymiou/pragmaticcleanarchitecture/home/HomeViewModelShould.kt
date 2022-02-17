/*
 * Copyright 2022 Petros Efthymiou Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.petros.efthymiou.pragmaticcleanarchitecture.home

import com.petros.efthymiou.pragmaticcleanarchitecture.home.application.presentation.HomeIntent
import com.petros.efthymiou.pragmaticcleanarchitecture.home.application.presentation.HomeState
import com.petros.efthymiou.pragmaticcleanarchitecture.home.application.presentation.HomeViewModel
import com.petros.efthymiou.pragmaticcleanarchitecture.home.utils.BaseUnitTestHome
import com.petros.efthymiou.pragmaticcleanarchitecture.home.utils.successfulState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class HomeViewModelShould : BaseUnitTestHome() {

    private val sut by inject<HomeViewModel>()

    @Test
    fun emitsLoadingStateInitially() {
        happyPath()

        val actual = sut.uiState.value

        assertEquals(HomeState.Loading, actual)
    }

    @Test
    fun emitSuccessfulStateAfterViewArticlesIntent() = runTest {
        happyPath()

        sut.handleIntent(HomeIntent.ViewAllArticles)

        val actual = sut.uiState.value

        assertEquals(successfulState, actual)
    }

    @Test
    fun emitEmptyStateWhenEmptyListIsReceived() = runTest {
        emptyResultPath()

        sut.handleIntent(HomeIntent.ViewAllArticles)

        val actual = sut.uiState.value

        assertEquals(HomeState.Empty, actual)
    }

    @Test
    fun notCrashAndNotPropagateError() = runTest {
        errorCase()

        sut.handleIntent(HomeIntent.ViewAllArticles)

        val actual = sut.uiState.value

        assertEquals(HomeState.Loading, actual)
    }
}

