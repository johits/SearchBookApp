package com.jhs.searchbookapp.domain.search.usecase

import androidx.paging.PagingData
import com.jhs.searchbookapp.domain.search.model.Book
import com.jhs.searchbookapp.domain.search.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchResultUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(query: String): Flow<PagingData<Book>> {
        return searchRepository.getBooks(query)
    }
}