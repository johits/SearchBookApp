package com.jhs.searchbookapp.domain.search.usecase

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jhs.searchbookapp.data.search.datasource.SearchDataSource
import com.jhs.searchbookapp.data.search.datasource.SearchPagingSource
import com.jhs.searchbookapp.domain.search.model.Book
import com.jhs.searchbookapp.domain.search.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchResultUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(query: String): Flow<PagingData<Book>> {
        return searchRepository.getBooks(query)
    }
}