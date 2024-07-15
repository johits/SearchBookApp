package com.jhs.searchbookapp.presentation.search.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
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
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        searchViewModel.getBooks("사랑")
    }
    val books = searchViewModel.booksState.collectAsLazyPagingItems()
//    val booksItem by remember { books }
//    val books = mainViewModel.bookPager.collectAsLazyPagingItems()
    LaunchedEffect(true) {
        searchViewModel.errorFlow.collectLatest { throwable -> onShowErrorSnackBar(throwable) }
    }

    Box(modifier = Modifier.fillMaxSize()) {
//        BookTopAppBar(
//            searchState = searchState,
//            onBackClick = onBackClick,
//        )
        BookContent(
            books = books,
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
    books: LazyPagingItems<Book>,
    onBookClick: (Book) -> Unit,
    modifier: Modifier = Modifier
) {
    when (books.loadState.refresh) {
        LoadState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        is LoadState.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "에러 발생")
            }
        }

        else -> {
            LazyColumn(
                modifier = modifier,
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                itemsIndexed(books) { index, item ->
                    item?.let {
                        BookItem(
                            item = item,
                            onItemClick = onBookClick
                        )
                    }
                }
//                bookItems(
//                    items = books,
//                    onItemClick = onBookClick
//                )
            }
        }
    }

}

//private fun LazyListScope.bookItems(
//    items: List<Book>,
//    onItemClick: (Book) -> Unit,
//) {
//    itemsIndexed(items) { index, item ->
//        item?.let {
//            BookItem(
//                item = item,
//                onItemClick = onItemClick
//            )
//        }
//    }
//
////    itemsIndexed(items) { index, item ->
////        BookItem(
////            item = item,
////            onItemClick = onItemClick
////        )
////    }
//}

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

