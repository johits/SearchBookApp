package com.jhs.searchbookapp.domain.bookmark.usecase

import com.jhs.searchbookapp.domain.bookmark.repository.BookmarkRepository
import com.jhs.searchbookapp.domain.search.model.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class UpdateBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {

    suspend operator fun invoke(book: Book, bookmark: Boolean) {
        withContext(Dispatchers.IO) {
            if (bookmark) {
                bookmarkRepository.insertBookmarkId(book.copy(isBookmarked = true))
            } else {
                bookmarkRepository.deleteBookmark(thumbnail = book.thumbnail, url = book.url)
            }
        }

    }
}
