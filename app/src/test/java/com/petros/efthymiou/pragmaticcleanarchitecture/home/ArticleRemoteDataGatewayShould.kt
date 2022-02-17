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

import com.petros.efthymiou.pragmaticcleanarchitecture.home.application.data.remote.ArticleDataSourceRemote
import com.petros.efthymiou.pragmaticcleanarchitecture.home.utils.BaseUnitTestHome
import com.petros.efthymiou.pragmaticcleanarchitecture.home.utils.articlesPlain
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.koin.test.inject

@ExperimentalCoroutinesApi
class ArticleRemoteDataGatewayShould: BaseUnitTestHome() {

    private val sut by inject<ArticleDataSourceRemote> ()

    @Test
    fun mapAndEmitArticlesPlainFromService() = runTest {
        happyPath()

        val actual = sut.fetchArticles().single()

        assertEquals(articlesPlain, actual)
    }

    @Test
    fun notCrashAndNotEmitErrors() = runTest {
        errorCase()

        val actual = sut.fetchArticles().count()

        assertEquals(0, actual)
    }
}