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

package com.petros.efthymiou.pragmaticcleanarchitecture.home.framework.remote

import com.petros.efthymiou.pragmaticcleanarchitecture.home.application.presentation.plain.ArticlePlain

data class ArticleRaw(
    val id: String,
    val title: String,
    val desc: String,
    val date: String,
    val words: Int,
    val category: String,
    val imageUrl: String,
    val stars: Int,
    val authorId: String,
    val canStar: Boolean,
    val otherJunkTheBackendIsSending: List<Any>?
)

{
    fun mapToPlain(): ArticlePlain =
        ArticlePlain(
            id = this.id,
            title = this.title,
            desc = this.desc,
            date = this.date,
            words = this.words,
            category = this.category,
            imageUrl = this.imageUrl,
            likes = this.stars,
            canLike = this.canStar,
            authorId = this.authorId
        )

    companion object {
        fun mapArticlesRawToPlain(articlesRaw: List<ArticleRaw>) : List<ArticlePlain> =
            articlesRaw.map {
                it.mapToPlain()
            }
    }
}
