package com.jhs.searchbookapp.presentation.search.screen

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
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

    val books = searchViewModel.booksState.collectAsLazyPagingItems()

    LaunchedEffect(true) {
        searchViewModel.errorFlow.collectLatest { throwable -> onShowErrorSnackBar(throwable) }
    }

    SearchForm()

    Box(modifier = Modifier.fillMaxSize()) {
        BookContent(
            books = books,
            modifier = Modifier
                .systemBarsPadding()
                .padding(top = 70.dp)
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
                Text(text = "검색 결과가 없습니다.")
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
            }
        }
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


@Composable
fun SearchField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "도서 검색"
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
fun SearchForm(
    viewModel: SearchViewModel = hiltViewModel()
) {
    Surface {
        var query by remember { mutableStateOf("") }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchField(
                value = query,
                onChange = { data -> query = data },
                modifier = Modifier
                    .weight(3f)
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    viewModel.apply {
                        saveQuery(query)
                        getBooks(query)
                    }

                },
                enabled = query.isNotEmpty(),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .weight(1f)
            ) {
                Text("검색")
            }
        }
    }
}
