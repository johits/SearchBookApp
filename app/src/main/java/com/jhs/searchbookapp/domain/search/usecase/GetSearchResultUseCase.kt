package com.jhs.searchbookapp.domain.search.usecase

import com.jhs.searchbookapp.domain.search.model.Book
import com.jhs.searchbookapp.domain.search.repository.SearchRepository
import javax.inject.Inject

class GetSearchResultUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(query: String): Result<List<Book>> {
        return kotlin.runCatching {
            searchRepository.getBooks(query)
        }
    }
}