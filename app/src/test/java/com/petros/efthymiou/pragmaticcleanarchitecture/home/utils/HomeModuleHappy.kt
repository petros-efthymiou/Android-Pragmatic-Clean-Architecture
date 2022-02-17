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
import com.petros.efthymiou.pragmaticcleanarchitecture.home.application.data.remote.ArticleDataSourceRemote
import com.petros.efthymiou.pragmaticcleanarchitecture.home.application.data.local.AuthorDataSourceLocal
import com.petros.efthymiou.pragmaticcleanarchitecture.home.application.data.remote.GetArticlesSourceRemote
import com.petros.efthymiou.pragmaticcleanarchitecture.home.application.data.remote.LikeArticleSourceRemote
import com.petros.efthymiou.pragmaticcleanarchitecture.home.application.presentation.HomeStateMapper
import com.petros.efthymiou.pragmaticcleanarchitecture.home.application.presentation.HomeViewModel
import com.petros.efthymiou.pragmaticcleanarchitecture.home.application.presentation.usecases.GetArticles
import com.petros.efthymiou.pragmaticcleanarchitecture.home.application.presentation.usecases.GetArticlesSource
import com.petros.efthymiou.pragmaticcleanarchitecture.home.application.presentation.usecases.LikeArticle
import com.petros.efthymiou.pragmaticcleanarchitecture.home.application.presentation.usecases.LikeArticleSource
import com.petros.efthymiou.pragmaticcleanarchitecture.home.framework.local.AuthorLocalDataGateway
import com.petros.efthymiou.pragmaticcleanarchitecture.home.framework.remote.ArticlesApi
import com.petros.efthymiou.pragmaticcleanarchitecture.home.framework.remote.ArticleRemoteDataGateway
import com.petros.efthymiou.pragmaticcleanarchitecture.home.framework.remote.ArticlesService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModuleHappy = module {

    single { GetArticles(get()) }
    single { LikeArticle(get()) }
    single { HomeStateMapper() }
    single<GetArticlesSource> { GetArticlesSourceRemote(get(), get()) }
    single<ArticleDataSourceRemote> { ArticleRemoteDataGateway(get()) }
    single<AuthorDataSourceLocal> { AuthorLocalDataGateway() }
    viewModel { HomeViewModel(get(), get(), get()) }
    single { ArticlesService(get()) }
    single<LikeArticleSource> { LikeArticleSourceRemote(get()) }
    single { provideArticlesApi() }
}

fun provideArticlesApi(): ArticlesApi = mock {
    onBlocking { fetchAllArticles() } doReturn articlesResultRaw
    onBlocking { likeArticle("articleId1") }
}


