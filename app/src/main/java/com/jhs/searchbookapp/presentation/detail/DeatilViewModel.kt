package com.jhs.searchbookapp.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhs.searchbookapp.domain.bookmark.usecase.GetBookmarkedBookIdsUseCase
import com.jhs.searchbookapp.domain.bookmark.usecase.UpdateBookmarkUseCase
import com.jhs.searchbookapp.domain.search.model.Book
import com.jhs.searchbookapp.domain.search.usecase.GetBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getBookUseCase: GetBookUseCase,
    private val getBookmarkedBookIdsUseCase: GetBookmarkedBookIdsUseCase,
    private val updateBookmarkUseCase: UpdateBookmarkUseCase,
) : ViewModel() {

    private val _detailUiState =
        MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState: StateFlow<DetailUiState> = _detailUiState

    private val _detailUiEffect = MutableStateFlow<DetailEffect>(DetailEffect.Idle)
    val detailUiEffect: StateFlow<DetailEffect> = _detailUiEffect

    init {
        viewModelScope.launch {
            combine(
                detailUiState,
                getBookmarkedBookIdsUseCase(),
            ) { detailUiState, bookmarks ->
                when (detailUiState) {
                    is DetailUiState.Loading -> detailUiState
                    is DetailUiState.Success -> {
                        detailUiState.copy(
                            bookmarked = bookmarks.contains(
                                detailUiState.book.copy(
                                    isBookmarked = true
                                )
                            )
                        )
                    }
                }
            }.collect { _detailUiState.value = it }
        }
    }

    fun fetchBook(bookId: String) {
        viewModelScope.launch {
            val book = getBookUseCase(bookId)
            _detailUiState.value = DetailUiState.Success(book)
        }
    }

    fun toggleBookmark() {
        val uiState = detailUiState.value
        if (uiState !is DetailUiState.Success) {
            return
        }
        viewModelScope.launch {
            val bookmark = uiState.bookmarked
            updateBookmarkUseCase(uiState.book, !bookmark)
            _detailUiEffect.value = DetailEffect.ShowToastForBookmarkState(!bookmark)
        }
    }

    fun updateBookmark(book: Book, isBookmark: Boolean) {
        viewModelScope.launch {
            updateBookmarkUseCase(book, isBookmark)
            _detailUiEffect.value = DetailEffect.ShowToastForBookmarkState(isBookmark)
        }
    }

    fun hidePopup() {
        viewModelScope.launch {
            _detailUiEffect.value = DetailEffect.Idle
        }
    }
}
