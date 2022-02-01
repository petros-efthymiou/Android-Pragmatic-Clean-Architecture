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

package com.petros.efthymiou.pragmaticcleanarchitecture.articles.framework.remote

import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.data.ArticlesDataSourceRemote
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.plain.ArticlePlain
import kotlinx.coroutines.flow.*


class ArticlesRemoteDataGateway(
    private val service: ArticlesService,
) : ArticlesDataSourceRemote {

    override suspend fun fetchArticles(): Flow<List<ArticlePlain>> =
        service.fetchArticles().map {
            ArticleRaw.mapArticlesRawToPlain(it)
        }

    override suspend fun likeArticle(articleId: String) = service.likeArticle(articleId)

}