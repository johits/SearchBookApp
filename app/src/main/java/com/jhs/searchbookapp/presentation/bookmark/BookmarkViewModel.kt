package com.jhs.searchbookapp.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhs.searchbookapp.domain.bookmark.usecase.GetBookmarkedBookIdsUseCase
import com.jhs.searchbookapp.domain.search.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getBookmarkedBookIdsUseCase: GetBookmarkedBookIdsUseCase,
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _bookmarkUiState = MutableStateFlow<List<Book>>(emptyList())
    val bookmarkUiState: StateFlow<List<Book>> = _bookmarkUiState

    init {
        viewModelScope.launch {
            getBookmarkedBookIdsUseCase().collect {
                _bookmarkUiState.emit(it)
            }
//
//            combine(
//                bookmarkUiState,
//                getBookmarkedBookIdsUseCase(),
//            ) { bookmarkUiState, bookmarkBook ->
//                when (bookmarkUiState) {
//                    is BookmarkUiState.Loading -> {
//                        BookmarkUiState.Success(
//                            isEditButtonSelected = false,
//                            bookmarks = bookmarkBook
//                                .mapIndexed { index, book ->
//                                    BookmarkItemUiState(
//                                        index = index,
//                                        book = book,
//                                        isEditMode = false
//                                    )
//                                }
//                        )
//                    }
//
//                    is BookmarkUiState.Success -> {
//                        bookmarkUiState.copy(
//                            bookmarks = bookmarkBook
//                                .mapIndexed { index, book ->
//                                    BookmarkItemUiState(
//                                        index = index,
//                                        book = book,
//                                        isEditMode = bookmarkUiState.isEditButtonSelected
//                                    )
//                                }
//                        )
//                    }
//                }
//            }.catch { throwable ->
//                _errorFlow.emit(throwable)
//            }.collect { _bookmarkUiState.value = it }
        }
    }

//    fun clickEditButton() {
//        val state = _bookmarkUiState.value
//        if (state !is BookmarkUiState.Success) {
//            return
//        }
//
//        _bookmarkUiState.value = state.copy(
//            isEditButtonSelected = state.isEditButtonSelected.not(),
//            bookmarks = state.bookmarks.map { it.copy(isEditMode = !it.isEditMode.not()) }
//        )
//    }
}
