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

package com.petros.efthymiou.pragmaticcleanarchitecture.articles.framework.di


import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.usecases.LikeArticle
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.data.*
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.*
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.usecases.GetArticles
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.usecases.GetArticlesSource
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.application.presentation.usecases.LikeArticleSource
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.framework.local.AuthorLocalDataGateway
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.framework.remote.ArticlesApi
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.framework.remote.ArticlesRemoteDataGateway
import com.petros.efthymiou.pragmaticcleanarchitecture.articles.framework.remote.ArticlesService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val homeModule = module {

    single { GetArticles(get()) }
    single { LikeArticle(get()) }
    single { HomeStateMapper() }
    single<GetArticlesSource> { GetArticlesSourceRemote(get(), get()) }
    single<ArticlesDataSourceRemote> { ArticlesRemoteDataGateway(get()) }
    single<AuthorDataSourceLocal> { AuthorLocalDataGateway() }
    viewModel { HomeViewModel(get(), get(), get()) }
    single { ArticlesService(get()) }
    single<LikeArticleSource> { LikeArticleSourceRemote(get()) }

    single { provideLoggingInterceptor() }
    single { provideHttpClient(get()) }
    single { provideArticlesApi(get()) }
    single { provideRetrofit(get()) }

}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return interceptor
}

private fun provideHttpClient(interceptor: HttpLoggingInterceptor) =
    OkHttpClient.Builder().readTimeout(20, TimeUnit.SECONDS).connectTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(interceptor).build()

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl("https://articles-clean.herokuapp.com/api/v2/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()


fun provideArticlesApi(retrofit: Retrofit): ArticlesApi = retrofit.create(ArticlesApi::class.java)
