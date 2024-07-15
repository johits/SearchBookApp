package com.jhs.searchbookapp.presentation.bookmark.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jhs.searchbookapp.R
import com.jhs.searchbookapp.domain.bookmark.model.Prices
import com.jhs.searchbookapp.domain.search.model.Book
import com.jhs.searchbookapp.presentation.bookmark.BookmarkViewModel
import com.jhs.searchbookapp.presentation.search.BookCard
import com.jhs.searchbookapp.presentation.ui.components.SearchBookAppTopAppBar
import com.jhs.searchbookapp.presentation.ui.components.TopAppBarNavigationType
import com.jhs.searchbookapp.presentation.ui.components.button.SortToggleButton
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
    onBookClick: (Book) -> Unit,
    searchViewModel: BookmarkViewModel = hiltViewModel(),
    bookmarkViewModel: BookmarkViewModel = hiltViewModel()
) {

    val bookmarks = searchViewModel.bookmarkUiState.collectAsStateWithLifecycle()
    val bookmarksItem by rememberSaveable { bookmarks }
    var sortIconClicked by remember { mutableStateOf(false) }
    val data = if (sortIconClicked) {
        bookmarksItem.sortedBy { it.title }
    } else {
        bookmarksItem.sortedByDescending { it.title }
    }

    LaunchedEffect(true) {
        searchViewModel.errorFlow.collectLatest { throwable -> onShowErrorSnackBar(throwable) }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        BookDetailTopAppBar(
            clickState = sortIconClicked,
            onClickIcon = {
                sortIconClicked = !sortIconClicked
                bookmarkViewModel.updateBookmarkUiState(data)
            }
        )
        SettingPriceForm(bookmarksItem)
        if (bookmarksItem.isEmpty()) {
            BookmarkEmptyScreen()
        }
        BookmarkContent(
            books = bookmarksItem,
            onBookClick = onBookClick,
        )

    }
}

@Composable
private fun BookDetailTopAppBar(
    clickState: Boolean,
    onClickIcon: (Boolean) -> Unit
) {
    SearchBookAppTopAppBar(
        titleRes = R.string.detail_title_sort,
        navigationIconContentDescription = null,
        navigationType = TopAppBarNavigationType.None,
        actionButtons = {
            SortToggleButton(
                clickState = clickState,
                onClick = onClickIcon
            )
        }
    )
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


@Composable
fun LowPriceField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "최저 금액"
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        label = { Text(label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None
    )
}

@Composable
fun HighPriceField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "최고 금액"
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        label = { Text(label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None
    )
}

@Composable
fun SettingPriceForm(
    bookmarks: List<Book>,
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    Surface {
        var prices by remember { mutableStateOf(Prices()) }
        val context = LocalContext.current

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LowPriceField(
                value = prices.lowPrice,
                onChange = { data -> prices = prices.copy(lowPrice = data) },
                modifier = Modifier
                    .width(100.dp)
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 15.dp),
                text = "~"
            )
            HighPriceField(
                value = prices.highPrice,
                onChange = { data -> prices = prices.copy(highPrice = data) },
                modifier = Modifier
                    .width(100.dp)

            )
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    val filteredList = bookmarks.filter {
                        val salePrice = if (it.sale_price > 0) it.sale_price else it.price
                        (salePrice.toLong() > prices.lowPrice.toLong()) && (salePrice.toLong() < prices.highPrice.toLong())
                    }
                    viewModel.updateBookmarkUiState(filteredList)
                    Log.e(
                        "금액설정",
                        "최저:${prices.lowPrice} // 최대:${prices.highPrice}\n결과:$filteredList"
                    )
                },
                enabled = prices.isNotEmpty(),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .width(90.dp)
            ) {
                Text("확인")
            }
        }
    }
}