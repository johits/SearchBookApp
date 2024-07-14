package com.jhs.searchbookapp.domain.search.usecase

import com.jhs.searchbookapp.domain.search.model.Book
import com.jhs.searchbookapp.domain.search.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchResultUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(query: String): Flow<List<Book>> {
        return flow {
            emit(searchRepository.getBooks(query))
        }
    }
}