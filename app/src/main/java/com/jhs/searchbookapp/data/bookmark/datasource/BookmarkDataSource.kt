package com.jhs.searchbookapp.data.bookmark.datasource

import com.jhs.searchbookapp.data.core.local.room.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface BookmarkDataSource {
    fun getBookmarkedBookIds(): Flow<List<BookmarkEntity>>
    suspend fun bookmarkBook(book: BookmarkEntity, bookmark: Boolean)

    suspend fun insertBookmarkId(bookmark: BookmarkEntity)
    suspend fun updateBookmarkId(bookmark: BookmarkEntity)

    suspend fun deleteBookmark(thumbnail: String, url: String)

    suspend fun deleteAllBookmark()
}