package com.jhs.searchbookapp.data.core.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jhs.searchbookapp.data.core.local.room.converter.BookmarkConverters
import com.jhs.searchbookapp.data.core.local.room.dao.BookmarkDao
import com.jhs.searchbookapp.data.core.local.room.entity.BookmarkEntity


@Database(entities = [BookmarkEntity::class], version = 1, exportSchema = false)
@TypeConverters(BookmarkConverters::class)
abstract class BookmarkDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}