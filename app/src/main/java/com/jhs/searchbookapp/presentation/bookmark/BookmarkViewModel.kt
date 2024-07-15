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
            refreshBookmarkUiState()
        }
    }

    fun updateBookmarkUiState(books: List<Book>) {
        viewModelScope.launch {
            _bookmarkUiState.emit(books)
        }
    }

    fun refreshBookmarkUiState() {
        viewModelScope.launch {
            getBookmarkedBookIdsUseCase().collect { list ->
                _bookmarkUiState.emit(list.sortedBy { it.title })
            }
        }
    }
}
