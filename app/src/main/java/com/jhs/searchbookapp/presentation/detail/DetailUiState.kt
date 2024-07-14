package com.jhs.searchbookapp.presentation.detail

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.jhs.searchbookapp.domain.search.model.Book

@Stable
sealed interface DetailUiState {
    @Immutable
    data object Loading : DetailUiState

    @Immutable
    data class Success(val book: Book, val bookmarked: Boolean = false) : DetailUiState
}
