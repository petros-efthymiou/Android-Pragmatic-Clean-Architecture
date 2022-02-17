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

import com.petros.efthymiou.pragmaticcleanarchitecture.home.framework.remote.ArticleRaw
import com.petros.efthymiou.pragmaticcleanarchitecture.home.utils.BaseUnitTestHome
import com.petros.efthymiou.pragmaticcleanarchitecture.home.utils.articlesPlain
import com.petros.efthymiou.pragmaticcleanarchitecture.home.utils.articlesResultRaw
import org.junit.Test
import kotlin.test.assertEquals

class ArticleRawShould : BaseUnitTestHome() {

    @Test
    fun mapArticlesRawToPlain() {
        val actual = ArticleRaw.mapArticlesRawToPlain(articlesResultRaw)

        assertEquals(articlesPlain, actual)
    }
}