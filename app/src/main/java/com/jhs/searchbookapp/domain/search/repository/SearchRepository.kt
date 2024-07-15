package com.jhs.searchbookapp.domain.search.repository

import androidx.paging.PagingData
import com.jhs.searchbookapp.domain.search.model.Book
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun getCachedBook(query: String): List<Book>
    suspend fun getBooks(query: String): Flow<PagingData<Book>>
    suspend fun getBook(bookId: String): Book

}