package com.jhs.searchbookapp.data.bookmark.datasource

import com.jhs.searchbookapp.data.core.local.room.dao.BookmarkDao
import com.jhs.searchbookapp.data.core.local.room.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkDataSourceImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao
) : BookmarkDataSource {
//    private val bookmarkIds: Flow<Set<String>> = sessionDataSource.bookmarkedSession
    override fun getBookmarkedBookIds(): Flow<List<BookmarkEntity>> {
        return bookmarkDao.getBookmarks()
    }

    override suspend fun bookmarkBook(book: BookmarkEntity, bookmark: Boolean) {
            if (bookmark) {
                bookmarkDao.insertBookmarkId(book)
            } else {
                bookmarkDao.deleteBookmarkId(book.isbn)
            }
    }

    override suspend fun insertBookmarkId(bookmark: BookmarkEntity) {
        bookmarkDao.insertBookmarkId(bookmark)
    }

    override suspend fun updateBookmarkId(bookmark: BookmarkEntity) {
        bookmarkDao.updateBookmarkId(bookmark)
    }

    override suspend fun deleteBookmarkId(bookmarkId: String) {
        bookmarkDao.deleteBookmarkId(bookmarkId)

    }

    override suspend fun deleteAllBookmark() {
        bookmarkDao.deleteAll()
    }
}

