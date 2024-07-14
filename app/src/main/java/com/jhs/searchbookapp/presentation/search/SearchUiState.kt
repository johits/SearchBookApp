package com.jhs.searchbookapp.presentation.search

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.jhs.searchbookapp.domain.search.model.Book

@Stable
sealed interface SearchUiState {

    @Immutable
    data object Loading : SearchUiState

    @Immutable
    data class Books(
        val books: List<Book> = listOf(),
    ) : SearchUiState
}
