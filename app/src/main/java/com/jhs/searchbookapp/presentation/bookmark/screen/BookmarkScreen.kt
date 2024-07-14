package com.jhs.searchbookapp.presentation.bookmark.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jhs.searchbookapp.domain.search.model.Book
import com.jhs.searchbookapp.presentation.bookmark.BookmarkViewModel
import com.jhs.searchbookapp.presentation.search.BookCard
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun BookmarkRoute(
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    viewModel: BookmarkViewModel = hiltViewModel(),
) {
    val bookmarkUiState by viewModel.bookmarkUiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { onShowErrorSnackBar(it) }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceDim)
            .systemBarsPadding()
    ) {
//        BookmarkContent(
//            bookmarks = bookmarkUiState,
//            onBookmarkClick = viewModel.bookmarkUiState
//        )
    }
}
@Composable
internal fun BookmarkScreen(
    onBackClick: () -> Unit,
    onBookClick: (Book) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    viewModel: BookmarkViewModel = hiltViewModel()
) {
//    LaunchedEffect(Unit) {
//        viewModel.getBooks("파과")
//    }
    val books = viewModel.bookmarkUiState.collectAsStateWithLifecycle()
    val booksItem by remember { books }


    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { throwable -> onShowErrorSnackBar(throwable) }
    }

    Box(modifier = Modifier.fillMaxSize()) {
//        BookTopAppBar(
//            searchState = searchState,
//            onBackClick = onBackClick,
//        )
//        BookmarkContent(
//            bookmarks = booksItem,
//            modifier = Modifier
//                .systemBarsPadding()
//                .padding(top = 48.dp)
//                .fillMaxSize(),
//            onBookmarkClick = onBookClick,
//        )
    }

}

@Composable
private fun BookmarkContent(
    bookmarks: List<Book>,
    onBookmarkClick: (Book) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        bookItems(
            items = bookmarks,
            onItemClick = onBookmarkClick,
        )
    }
}

private fun LazyListScope.bookItems(
    items: List<Book>,
    onItemClick: (Book) -> Unit,
) {
    itemsIndexed(items) { index, item ->
        BookItem(
            item = item,
            onItemClick = onItemClick
        )
    }
}

@Composable
private fun BookItem(
    item: Book,
    onItemClick: (Book) -> Unit,
) {
    Column {
        BookCard(book = item, onBookClick = onItemClick)
    }
}

