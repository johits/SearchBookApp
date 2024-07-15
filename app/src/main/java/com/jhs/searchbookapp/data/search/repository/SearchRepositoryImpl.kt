package com.jhs.searchbookapp.data.search.repository

import androidx.paging.PagingData
import com.jhs.searchbookapp.data.core.constant.DataStoreKey
import com.jhs.searchbookapp.data.core.local.datastore.DataStoreManager
import com.jhs.searchbookapp.data.search.datasource.SearchDataSource
import com.jhs.searchbookapp.domain.search.model.Book
import com.jhs.searchbookapp.domain.search.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource,
    private val dataStoreManager: DataStoreManager
) : SearchRepository {
    override suspend fun getCachedBook(query: String): List<Book> {
        return searchDataSource.getCachedBook(query)
    }

    override suspend fun getBooks(
        query: String
    ): Flow<PagingData<Book>> {
        return searchDataSource.getPagingBooks(query)
    }

    override suspend fun getBook(bookId: String): Book {
        val query = dataStoreManager.fetchSyncDataStoreString(DataStoreKey.QUERY)
        return getCachedBook(query).find { it.isbn == bookId }
            ?: error("정보 불러오기에 실패하였습니다.")
    }
}