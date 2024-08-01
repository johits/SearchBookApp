package com.jhs.searchbookapp.data.search.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jhs.searchbookapp.data.search.mapper.toModel
import com.jhs.searchbookapp.data.search.service.SearchBookApi
import com.jhs.searchbookapp.domain.search.model.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val searchBookApi: SearchBookApi,
) : SearchDataSource {
    override suspend fun getCachedBook(query: String): List<Book> {
        return searchBookApi.getBookData(query = query, page = 1, size = 20).documents
            .map { it.toModel() }
    }

    override suspend fun getPagingBooks(
        query: String
    ): Flow<PagingData<Book>> {
        return Pager(
            config = PagingConfig(pageSize = 1, enablePlaceholders = false),
            pagingSourceFactory = { SearchPagingSource(searchBookApi, query) }
        ).flow
    }
}

