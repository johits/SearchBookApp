package com.jhs.searchbookapp.domain.search.usecase

import com.jhs.searchbookapp.domain.search.model.Book
import com.jhs.searchbookapp.domain.search.repository.SearchRepository
import javax.inject.Inject
class GetBookUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(bookId: String): Book {
        return searchRepository.getBook(bookId)
    }
}