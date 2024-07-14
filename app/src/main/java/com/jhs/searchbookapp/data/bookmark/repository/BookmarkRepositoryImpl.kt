package com.jhs.searchbookapp.data.bookmark.repository

import com.jhs.searchbookapp.data.bookmark.datasource.BookmarkDataSource
import com.jhs.searchbookapp.data.bookmark.mapper.toEntity
import com.jhs.searchbookapp.data.bookmark.mapper.toModel
import com.jhs.searchbookapp.domain.bookmark.repository.BookmarkRepository
import com.jhs.searchbookapp.domain.search.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarkDataSource: BookmarkDataSource
) : BookmarkRepository {
    override fun getBookmarkedBookIds(): Flow<List<Book>> {
        return bookmarkDataSource.getBookmarkedBookIds().map { list ->
            list.map { it.toModel() }
        }
    }

    override suspend fun bookmarkBook(book: Book, bookmark: Boolean) {
        return bookmarkDataSource.bookmarkBook(book.toEntity(), bookmark)
    }

    override suspend fun insertBookmarkId(book: Book) {
        return bookmarkDataSource.insertBookmarkId(book.toEntity())
    }

    override suspend fun updateBookmarkId(book: Book) {
        return bookmarkDataSource.updateBookmarkId(book.toEntity())
    }

    override suspend fun deleteBookmarkId(bookmarkId: String) {
        return bookmarkDataSource.deleteBookmarkId(bookmarkId)
    }

    override suspend fun deleteAllBookmark() {
        return bookmarkDataSource.deleteAllBookmark()
    }

}