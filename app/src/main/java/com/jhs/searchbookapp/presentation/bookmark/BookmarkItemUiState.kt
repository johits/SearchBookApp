package com.jhs.searchbookapp.presentation.bookmark

import com.jhs.searchbookapp.domain.search.model.Book
import javax.annotation.concurrent.Immutable


@Immutable
data class BookmarkItemUiState(
    val index: Int,
    val book: Book,
    val isEditMode: Boolean,
) {
    val sequence: Int
        get() = index + 1

//    val tagLabel: String
//        get() = book.tags.joinToString { it.name }
//
//    val speakerLabel: String
//        get() = book.speakers.joinToString { it.name }
//
//    val time: LocalTime
//        get() = book.startTime.toJavaLocalDateTime().toLocalTime()
}
