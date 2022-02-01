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

package com.petros.efthymiou.pragmaticcleanarchitecture.home.utils

import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.Article
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.HomeState
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.plain.ArticlePlain
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.plain.ArticlesAuthors
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.plain.AuthorPlain
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.framework.remote.ArticleRaw

val articlesResultRaw: List<ArticleRaw> = listOf(
    ArticleRaw(
        "id1",
        "title1",
        "desc1",
        "2000-10-20",
        4000,
        "sports",
        "imageUrl1",
        455,
        "authorId1",
        true,
        listOf("junk1", "junk2")
    ),
    ArticleRaw(
        "id2",
        "title2",
        "desc2",
        "2000-5-25",
        3000,
        "music",
        "imageUrl2",
        555,
        "authorId2",
        false,
        listOf("junk3", "junk4")
    ),
)

val articlesPlain: List<ArticlePlain> = listOf(
    ArticlePlain(
        "id1",
        "title1",
        "desc1",
        "2000-10-20",
        4000,
        "sports",
        "imageUrl1",
        455,
        true,
        "authorId1",
    ),
    ArticlePlain(
        "id2",
        "title2",
        "desc2",
        "2000-5-25",
        3000,
        "music",
        "imageUrl2",
        555,
        false,
        "authorId2",
    ),
)

val authorsPlain: List<AuthorPlain> = listOf(
    AuthorPlain(
        "authorId1",
        "Petros Efthymiou",
        5,
    ),
    AuthorPlain(
        "authorId2",
        "Nikos Voulgaris",
        5,
    ),
    AuthorPlain(
        "authorId3",
        "Tom Edgard",
        5,
    ),
    AuthorPlain(
        "authorId4",
        "Donny Wals",
        5,
    )
)

val articlesAuthors = ArticlesAuthors(
    articlesPlain,
    authorsPlain
)

val articles: List<Article> = listOf(
    Article(
        "id1",
        "title1",
        "desc1",
        "20-10-2000",
        4000 / 200,
        "sports",
        "imageUrl1",
        455,
        "Petros Efthymiou",
        true,
        1F
    ),
    Article(
        "id2",
        "title2",
        "desc2",
        "25-05-2000",
        3000 / 200,
        "music",
        "imageUrl2",
        555,
        "Nikos Voulgaris",
        false,
        0.2F
    ),
)

val successfulState = HomeState.Success(articles)