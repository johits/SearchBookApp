package com.jhs.searchbookapp.data.search.di

import com.jhs.searchbookapp.data.search.service.SearchBookApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchApiModule {
    @Provides
    @Singleton
    fun provideSearchService(
        retrofit: Retrofit
    ): SearchBookApi =
        retrofit.create(SearchBookApi::class.java)
}