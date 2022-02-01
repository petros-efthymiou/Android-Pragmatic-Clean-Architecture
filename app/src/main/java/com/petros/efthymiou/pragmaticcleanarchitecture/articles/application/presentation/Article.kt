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

package com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation

import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.plain.ArticlePlain
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.plain.ArticlesAuthors
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.plain.AuthorPlain
import java.text.SimpleDateFormat

data class Article(
    val id: String,
    val title: String,
    val desc: String,
    val date: String,
    val readTime: Int,
    val category: String,
    val imageUrl: String,
    val likes: Int,
    val authorName: String,
    val canLike: Boolean,
    val likeActionAlpha: Float
) {
    companion object {
        fun mapArticlesFromData(data: ArticlesAuthors): List<Article> =
            data.articlePlain.map { mapArticle(it, data.authorsPlain) }

        private fun mapArticle(
            plain: ArticlePlain,
            authorsPlain: List<AuthorPlain>,
        ): Article =
            Article(
                id = plain.id,
                title = plain.title,
                desc = plain.desc,
                date = mapDate(plain.date),
                readTime = mapReadTime(plain.words),
                category = plain.category,
                imageUrl = plain.imageUrl,
                likes = plain.likes,
                authorName = mapAuthorName(plain.authorId, authorsPlain),
                canLike = plain.canLike,
                likeActionAlpha = mapLikeAlpha(plain.canLike)
            )

        @Suppress("SimpleDateFormat")
        private fun mapDate(date: String): String =
            SimpleDateFormat("dd-MM-yyyy").format(SimpleDateFormat("yyyy-MM-dd").parse(date))

        private fun mapReadTime(words: Int) = words / 200


        private fun mapAuthorName(authorId: String, authors: List<AuthorPlain>): String =
            authors.find { author -> author.id == authorId }?.fullName ?: "-"

        private fun mapLikeAlpha(canLike: Boolean): Float {
            return when (canLike) {
                true -> 1F
                false -> 0.2F
            }
        }
    }
}