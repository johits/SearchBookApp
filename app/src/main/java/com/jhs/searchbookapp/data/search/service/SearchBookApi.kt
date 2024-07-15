package com.jhs.searchbookapp.data.search.service

import com.jhs.searchbookapp.data.search.entity.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchBookApi {
    @GET("v3/search/book")
    suspend fun getBookData(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): BooksResponse
}