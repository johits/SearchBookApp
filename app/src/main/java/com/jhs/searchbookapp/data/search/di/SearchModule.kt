package com.jhs.searchbookapp.data.search.di

import com.jhs.searchbookapp.data.search.datasource.SearchDataSource
import com.jhs.searchbookapp.data.search.datasource.SearchDataSourceImpl
import com.jhs.searchbookapp.data.search.repository.SearchRepositoryImpl
import com.jhs.searchbookapp.domain.search.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchModule {

    @Binds
    abstract fun bindSearchDataSource(
        impl: SearchDataSourceImpl
    ): SearchDataSource

    @Binds
    abstract fun bindSearchRepository(
        impl: SearchRepositoryImpl
    ): SearchRepository
}