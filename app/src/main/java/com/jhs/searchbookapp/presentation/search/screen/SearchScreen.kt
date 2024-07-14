package com.jhs.searchbookapp.presentation.search.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jhs.searchbookapp.domain.search.model.Book
import com.jhs.searchbookapp.presentation.search.BookCard
import com.jhs.searchbookapp.presentation.search.SearchViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
internal fun SearchRoute(
    padding: PaddingValues,
    onBackClick: () -> Unit,
    onBookClick: (Book) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { throwable -> onShowErrorSnackBar(throwable) }
    }

    SearchScreen(
        onBookClick = onBookClick,
        onShowErrorSnackBar = onShowErrorSnackBar
    )
}

@Composable
internal fun SearchScreen(
    onBookClick: (Book) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        searchViewModel.getBooks("파과")
    }
    val books = searchViewModel.booksState.collectAsStateWithLifecycle()
    val booksItem by remember { books }

    LaunchedEffect(true) {
        searchViewModel.errorFlow.collectLatest { throwable -> onShowErrorSnackBar(throwable) }
    }

    Box(modifier = Modifier.fillMaxSize()) {
//        BookTopAppBar(
//            searchState = searchState,
//            onBackClick = onBackClick,
//        )
        BookContent(
            books = booksItem,
            modifier = Modifier
                .systemBarsPadding()
                .padding(top = 48.dp)
                .fillMaxSize(),
            onBookClick = onBookClick
        )
    }
}

@Composable
private fun BookContent(
    books: List<Book>,
    onBookClick: (Book) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        bookItems(
            items = books,
            onItemClick = onBookClick
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
    onItemClick: (Book) -> Unit
) {
    Column {
        BookCard(
            book = item,
            onBookClick = onItemClick
        )
    }
}

