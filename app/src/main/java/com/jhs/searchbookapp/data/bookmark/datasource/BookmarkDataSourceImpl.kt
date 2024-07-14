package com.jhs.searchbookapp.data.bookmark.datasource

import com.jhs.searchbookapp.data.core.local.room.dao.BookmarkDao
import com.jhs.searchbookapp.data.core.local.room.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkDataSourceImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao
) : BookmarkDataSource {
    override fun getBookmarkedBookIds(): Flow<List<BookmarkEntity>> {
        return bookmarkDao.getBookmarks()
    }

    override suspend fun bookmarkBook(book: BookmarkEntity, bookmark: Boolean) {
        if (bookmark) {
            bookmarkDao.insertBookmarkId(book.copy(isBookmarked = true))
        } else {
            bookmarkDao.deleteBookmark(thumbnail = book.thumbnail, url = book.url)
        }
    }

    override suspend fun insertBookmarkId(bookmark: BookmarkEntity) {
        bookmarkDao.insertBookmarkId(bookmark.copy(isBookmarked = true))
    }

    override suspend fun updateBookmarkId(bookmark: BookmarkEntity) {
        bookmarkDao.updateBookmarkId(bookmark)
    }

    override suspend fun deleteBookmark(thumbnail: String, url: String) {
        bookmarkDao.deleteBookmark(thumbnail, url)
    }


    override suspend fun deleteAllBookmark() {
        bookmarkDao.deleteAll()
    }
}

