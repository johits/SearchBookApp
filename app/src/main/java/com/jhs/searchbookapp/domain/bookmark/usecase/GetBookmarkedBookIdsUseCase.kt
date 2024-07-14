package com.jhs.searchbookapp.domain.bookmark.usecase

import com.jhs.searchbookapp.domain.bookmark.repository.BookmarkRepository
import com.jhs.searchbookapp.domain.search.model.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarkedBookIdsUseCase @Inject constructor(
    private val roomBookmarkRepository: BookmarkRepository
) {

    operator fun invoke(): Flow<List<Book>> {
        return roomBookmarkRepository.getBookmarkedBookIds()
    }
}
