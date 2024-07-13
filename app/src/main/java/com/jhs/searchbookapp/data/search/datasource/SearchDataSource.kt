package com.jhs.searchbookapp.data.search.datasource

import com.jhs.searchbookapp.data.search.entity.BookResponse

interface SearchDataSource {
    suspend fun getBooks(query: String): List<BookResponse>
}