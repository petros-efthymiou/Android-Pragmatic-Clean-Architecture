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

package com.petros.efthymiou.pragmaticcleanarchitecture.home.framework.local

import com.petros.efthymiou.pragmaticcleanarchitecture.home.application.presentation.plain.AuthorPlain

data class AuthorLocal(
    val id: String,
    val firstName: String,
    val lastName: String,
    val rating: Int,
    val junkThatWeStoreLocally: String
) {
    fun toPlain(): AuthorPlain =
        AuthorPlain(
            this.id,
            mapFullName(this.firstName, this.lastName),
            this.rating
        )

    private fun mapFullName(firstName: String, lastName: String): String =
        "$firstName $lastName"

    companion object {
        fun mapAuthorsLocalToPlain(authorsLocal: List<AuthorLocal>): List<AuthorPlain> =
            authorsLocal.map {
                it.toPlain()
            }
    }
}