package com.jhs.searchbookapp.data.search.repository

import androidx.paging.PagingData
import com.jhs.searchbookapp.data.search.datasource.SearchDataSource
import com.jhs.searchbookapp.domain.search.model.Book
import com.jhs.searchbookapp.domain.search.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource
) : SearchRepository {
    override suspend fun getCachedBook(query: String): List<Book> {
        return searchDataSource.getCachedBook(query)
    }

    override suspend fun getBooks(
        query: String
    ): Flow<PagingData<Book>> {
        return searchDataSource.getPagingBooks(query)
    }
}