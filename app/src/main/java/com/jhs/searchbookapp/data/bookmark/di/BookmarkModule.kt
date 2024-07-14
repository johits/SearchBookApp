package com.jhs.searchbookapp.data.bookmark.di

import com.jhs.searchbookapp.data.bookmark.datasource.BookmarkDataSource
import com.jhs.searchbookapp.data.bookmark.datasource.BookmarkDataSourceImpl
import com.jhs.searchbookapp.data.bookmark.repository.BookmarkRepositoryImpl
import com.jhs.searchbookapp.domain.bookmark.repository.BookmarkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BookmarkModule {
    @Binds
    abstract fun bindBookmarkDataSource(
        impl: BookmarkDataSourceImpl
    ): BookmarkDataSource

    @Binds
    abstract fun bindBookmarkRepository(
        impl: BookmarkRepositoryImpl
    ): BookmarkRepository
}