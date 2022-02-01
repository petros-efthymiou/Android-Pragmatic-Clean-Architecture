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

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.data.ArticlesDataSourceRemote
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.data.AuthorDataSourceLocal
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.data.GetArticlesSourceRemote
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.data.LikeArticleSourceRemote
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.HomeStateMapper
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.HomeViewModel
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.usecases.GetArticles
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.usecases.GetArticlesSource
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.usecases.LikeArticle
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.usecases.LikeArticleSource
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.framework.local.AuthorLocalDataGateway
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.framework.remote.ArticlesApi
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.framework.remote.ArticlesRemoteDataGateway
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.framework.remote.ArticlesService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModuleEmpty =  module {

    single { GetArticles(get()) }
    single { LikeArticle(get()) }
    single { HomeStateMapper() }
    single<GetArticlesSource> { GetArticlesSourceRemote(get(), get()) }
    single<ArticlesDataSourceRemote> { ArticlesRemoteDataGateway(get()) }
    single<AuthorDataSourceLocal> { AuthorLocalDataGateway() }
    viewModel { HomeViewModel(get(), get(), get()) }
    single { ArticlesService(get()) }
    single<LikeArticleSource> { LikeArticleSourceRemote(get()) }
    single { provideArticlesApiEmpty() }
}

fun provideArticlesApiEmpty(): ArticlesApi = mock {
    onBlocking { fetchAllArticles() } doReturn emptyList()
}
