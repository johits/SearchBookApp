package com.jhs.searchbookapp.data.search.datasource

import com.jhs.searchbookapp.data.core.di.IODispatcher
import com.jhs.searchbookapp.data.search.entity.BookResponse
import com.jhs.searchbookapp.data.search.service.SearchBookApi
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val searchBookApi: SearchBookApi,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : SearchDataSource {

    override suspend fun getBooks(query: String): List<BookResponse> =
        searchBookApi.getBookData(query).documents
}

