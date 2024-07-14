package com.jhs.searchbookapp.domain.bookmark.repository

import com.jhs.searchbookapp.domain.search.model.Book
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    fun getBookmarkedBookIds(): Flow<List<Book>>
    suspend fun bookmarkBook(book: Book, bookmark: Boolean)

    suspend fun insertBookmarkId(bookmark: Book)
    suspend fun updateBookmarkId(bookmark: Book)

    suspend fun deleteBookmarkId(bookmarkId: String)

    suspend fun deleteAllBookmark()
}