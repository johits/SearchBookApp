package com.jhs.searchbookapp.data.search.datasource

import androidx.paging.PagingData
import com.jhs.searchbookapp.domain.search.model.Book
import kotlinx.coroutines.flow.Flow

interface SearchDataSource {
    suspend fun getCachedBook(query: String): List<Book>
    suspend fun getPagingBooks(query: String): Flow<PagingData<Book>>
}