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

import com.petros.efthymiou.pragmaticcleanarchitecture.home.application.presentation.usecases.GetArticlesSource
import com.petros.efthymiou.pragmaticcleanarchitecture.home.utils.BaseUnitTestHome
import com.petros.efthymiou.pragmaticcleanarchitecture.home.utils.articlesAuthors
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals


@ExperimentalCoroutinesApi
class GetArticlesSourceRemoteShould : BaseUnitTestHome() {

    private val source by inject<GetArticlesSource>()

    @Test
    fun combineArticleData() = runTest {
        happyPath()

        val actual = source.articles().single()
        assertEquals(articlesAuthors, actual)
    }

    @Test
    fun doesNotEmitInCaseOfError() = runTest {
        errorCase()

        val actual = source.articles().count()
        assertEquals(0, actual)
    }
}