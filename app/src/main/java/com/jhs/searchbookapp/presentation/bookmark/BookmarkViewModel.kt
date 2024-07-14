package com.jhs.searchbookapp.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhs.searchbookapp.domain.bookmark.usecase.GetBookmarkedBookIdsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.combine

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getBookmarkedBookIdsUseCase: GetBookmarkedBookIdsUseCase,
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _bookmarkUiState = MutableStateFlow<BookmarkUiState>(BookmarkUiState.Loading)
    val bookmarkUiState: StateFlow<BookmarkUiState> = _bookmarkUiState

    init {
        viewModelScope.launch {
            combine(
                bookmarkUiState,
                getBookmarkedBookIdsUseCase(),
            ) { bookmarkUiState, bookmarkBook ->
                when (bookmarkUiState) {
                    is BookmarkUiState.Loading -> {
                        BookmarkUiState.Success(
                            isEditButtonSelected = false,
                            bookmarks = bookmarkBook
                                .mapIndexed { index, book ->
                                    BookmarkItemUiState(
                                        index = index,
                                        book = book,
                                        isEditMode = false
                                    )
                                }
                        )
                    }

                    is BookmarkUiState.Success -> {
                        bookmarkUiState.copy(
                            bookmarks = bookmarkBook
                                .mapIndexed { index, book ->
                                    BookmarkItemUiState(
                                        index = index,
                                        book = book,
                                        isEditMode = bookmarkUiState.isEditButtonSelected
                                    )
                                }
                        )
                    }
                }
            }.catch { throwable ->
                _errorFlow.emit(throwable)
            }.collect { _bookmarkUiState.value = it }
        }
    }

    fun clickEditButton() {
        val state = _bookmarkUiState.value
        if (state !is BookmarkUiState.Success) {
            return
        }

        _bookmarkUiState.value = state.copy(
            isEditButtonSelected = state.isEditButtonSelected.not(),
            bookmarks = state.bookmarks.map { it.copy(isEditMode = !it.isEditMode.not()) }
        )
    }
}
