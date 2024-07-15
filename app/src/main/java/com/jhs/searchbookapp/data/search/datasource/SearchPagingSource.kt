package com.jhs.searchbookapp.data.search.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jhs.searchbookapp.data.search.mapper.toModel
import com.jhs.searchbookapp.data.search.service.SearchBookApi
import com.jhs.searchbookapp.domain.search.model.Book

class SearchPagingSource(
    private val searchBookApi: SearchBookApi,
    private val query: String
) : PagingSource<Int, Book>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        return try {
            val nextPageNumber = params.key ?: 1 // 초기 페이지 번호는 1로 설정
            val response = searchBookApi.getBookData(query, nextPageNumber, 20).documents.map { it.toModel() }
            val prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1
            LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}