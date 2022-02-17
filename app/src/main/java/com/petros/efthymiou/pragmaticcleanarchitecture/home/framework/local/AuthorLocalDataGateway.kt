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

import com.petros.efthymiou.pragmaticcleanarchitecture.home.application.data.local.AuthorDataSourceLocal
import com.petros.efthymiou.pragmaticcleanarchitecture.home.application.presentation.plain.AuthorPlain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthorLocalDataGateway : AuthorDataSourceLocal {

    override suspend fun findAuthors(): Flow<List<AuthorPlain>> =
        flow {
            emit(
                AuthorLocal.mapAuthorsLocalToPlain(
                    listOf(
                        AuthorLocal(
                            "authorId1",
                            "Petros",
                            "Efthymiou",
                            5,
                            "stored junk 1"
                        ),
                        AuthorLocal(
                            "authorId2",
                            "Nikos",
                            "Voulgaris",
                            5,
                            "stored junk 2"
                        ),
                        AuthorLocal(
                            "authorId3",
                            "Christos",
                            "Smpyrakos",
                            5,
                            "stored junk 3"
                        ),
                        AuthorLocal(
                            "authorId4",
                            "Donny",
                            "Wals",
                            5,
                            "stored junk 4"
                        ),
                    )
                )
            )
        }
}
