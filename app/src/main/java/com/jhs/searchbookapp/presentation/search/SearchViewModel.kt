package com.jhs.searchbookapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhs.searchbookapp.domain.bookmark.usecase.GetBookmarkedBookIdsUseCase
import com.jhs.searchbookapp.domain.search.model.Book
import com.jhs.searchbookapp.domain.search.usecase.GetSearchResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchResultUseCase: GetSearchResultUseCase,
    private val getBookmarkedBookIdsUseCase: GetBookmarkedBookIdsUseCase
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _booksState = MutableStateFlow<List<Book>>(emptyList())
    val booksState: StateFlow<List<Book>> = _booksState

    fun getBooks(query: String) {
        viewModelScope.launch {
            val booksFlow = getSearchResultUseCase(query)
            val bookmarkedIdsFlow = getBookmarkedBookIdsUseCase()

            booksFlow.combine(bookmarkedIdsFlow) { books, bookmarkedIds ->
                val enhancedBooks = books.map { book ->
                    book.copy(isBookmarked = bookmarkedIds.contains(book.copy(isBookmarked = true)))
                }
                SearchUiState.Books(
                    books = enhancedBooks.toPersistentList()
                )
            }.catch { throwable ->
                _errorFlow.emit(throwable)
            }.collect { combinedUiState ->
                _booksState.value = combinedUiState.books
            }
        }
    }
}
