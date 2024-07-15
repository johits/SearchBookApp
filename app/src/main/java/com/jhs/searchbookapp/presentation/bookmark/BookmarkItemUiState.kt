package com.jhs.searchbookapp.presentation.bookmark

import com.jhs.searchbookapp.domain.search.model.Book
import javax.annotation.concurrent.Immutable


@Immutable
data class BookmarkItemUiState(
    val index: Int,
    val book: Book,
    val isEditMode: Boolean,
)
