package com.jhs.searchbookapp.data.core.di

import android.content.Context
import androidx.room.Room
import com.jhs.searchbookapp.data.core.local.room.BookmarkDatabase
import com.jhs.searchbookapp.data.core.local.room.dao.BookmarkDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBookmarkDao(bookmarkDatabase: BookmarkDatabase): BookmarkDao = bookmarkDatabase.bookmarkDao()


    /**
     * Room DataBase
     */
    @Provides
    @Singleton
    fun provideRoomDataBase(@ApplicationContext applicationContext: Context): BookmarkDatabase {
        return Room.databaseBuilder(
            applicationContext,
            BookmarkDatabase::class.java,
            "database"
        ).build()
    }
}