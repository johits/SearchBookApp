package com.jhs.searchbookapp.data.search.repository

import com.jhs.searchbookapp.data.search.datasource.SearchDataSource
import com.jhs.searchbookapp.data.search.mapper.toModel
import com.jhs.searchbookapp.domain.search.model.Book
import com.jhs.searchbookapp.domain.search.repository.SearchRepository
import javax.inject.Inject


class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource
) : SearchRepository {

    private var cachedBooks: List<Book> = emptyList()

    override suspend fun getBooks(query: String): List<Book> {
        return searchDataSource.getBooks(query).map { it.toModel() }.also { cachedBooks = it }
    }

    override suspend fun getBook(bookId: String): Book {
        val cachedBook = cachedBooks.find { it.isbn ==  bookId}
//        if (cachedBook != null){
//            return cachedBook
//        }
        return cachedBook ?: error("정보 불러오기에 실패하였습니다.")
    }
}