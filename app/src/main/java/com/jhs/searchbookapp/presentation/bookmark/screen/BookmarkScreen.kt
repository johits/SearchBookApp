package com.jhs.searchbookapp.presentation.bookmark.screen

import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jhs.searchbookapp.R
import com.jhs.searchbookapp.domain.search.model.Book
import com.jhs.searchbookapp.presentation.bookmark.BookmarkViewModel
import com.jhs.searchbookapp.presentation.detail.DetailBookmarkStatePopup
import com.jhs.searchbookapp.presentation.detail.DetailEffect
import com.jhs.searchbookapp.presentation.detail.DetailViewModel
import com.jhs.searchbookapp.presentation.search.BookCard
import com.jhs.searchbookapp.presentation.ui.theme.SearchBookAppTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun BookmarkRoute(
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    onBookClick: (Book) -> Unit,
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { onShowErrorSnackBar(it) }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceDim)
            .systemBarsPadding()
    ) {
        BookmarkScreen(
            onShowErrorSnackBar = onShowErrorSnackBar,
            onBookClick = onBookClick
        )
    }
}

@Composable
internal fun BookmarkScreen(
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    listContentBottomPadding: Dp = 72.dp,
    onBookClick: (Book) -> Unit,
    searchViewModel: BookmarkViewModel = hiltViewModel(),
    detailViewModel: DetailViewModel = hiltViewModel(),
) {
//    LaunchedEffect(Unit) {
//        viewModel.getBooks("파과")
//    }
    val bookmarks = searchViewModel.bookmarkUiState.collectAsStateWithLifecycle()
    val bookmarksItem by remember { bookmarks }
    val effect by detailViewModel.detailUiEffect.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        searchViewModel.errorFlow.collectLatest { throwable -> onShowErrorSnackBar(throwable) }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
//        BookmarkTopAppBar(isEditMode = isEditMode, onClickEditButton = onClickEditButton)

        if (bookmarksItem.isEmpty()) {
            BookmarkEmptyScreen()
        }

        BookmarkContent(
            books = bookmarksItem,
            onBookClick = onBookClick,
        )

//        Box {
//            if (effect is DetailEffect.ShowToastForBookmarkState) {
//                DetailBookmarkStatePopup(
//                    bookmarked = (effect as DetailEffect.ShowToastForBookmarkState).bookmarked
//                )
//            }
//        }

    }
}

@Composable
private fun BookmarkContent(
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
            onBookClick = onBookClick
        )
    }
}

private fun LazyListScope.bookItems(
    items: List<Book>,
    onBookClick: (Book) -> Unit
) {
    itemsIndexed(items) { index, item ->
        BookItem(
            book = item,
            onBookClick = onBookClick
        )
    }
}

@Composable
private fun BookItem(
    book: Book,
    onBookClick: (Book) -> Unit
) {
    Column {
        BookCard(
            book = book,
            onBookClick = onBookClick
        )
    }
}

@Composable
private fun BookmarkEmptyScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.empty_bookmark_item_description),
            style = SearchBookAppTheme.typography.titleSmallM,
            color = Color.Gray
        )
    }
}