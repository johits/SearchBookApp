package com.jhs.searchbookapp.domain.search.repository

import com.jhs.searchbookapp.domain.search.model.Book

interface SearchRepository {

    suspend fun getBooks(query: String): List<Book>

    suspend fun getBook(bookId: String): Book
}