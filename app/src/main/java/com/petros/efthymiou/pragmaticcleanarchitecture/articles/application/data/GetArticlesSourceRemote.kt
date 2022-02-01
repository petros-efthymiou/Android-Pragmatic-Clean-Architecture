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

package com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.data

import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.usecases.GetArticlesSource
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.plain.ArticlesAuthors
import kotlinx.coroutines.flow.*

class GetArticlesSourceRemote constructor(
    private val articlesRemoteSource: ArticlesDataSourceRemote,
    private val authorLocalSource: AuthorDataSourceLocal
) : GetArticlesSource {

    override suspend fun articles(): Flow<ArticlesAuthors> {
        val articlesSource = articlesRemoteSource.fetchArticles()
        val authorsSource = authorLocalSource.findAuthors()

        return combine(articlesSource, authorsSource) {
                articles, authors ->
                    ArticlesAuthors(articles, authors)
        }
    }
}