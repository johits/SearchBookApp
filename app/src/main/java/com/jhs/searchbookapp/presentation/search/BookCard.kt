package com.jhs.searchbookapp.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhs.searchbookapp.domain.search.model.Book
import com.jhs.searchbookapp.presentation.detail.DetailViewModel
import com.jhs.searchbookapp.presentation.detail.screen.BookDetailContentProvider
import com.jhs.searchbookapp.presentation.ui.components.DefaultCard
import com.jhs.searchbookapp.presentation.ui.components.NetworkImage
import com.jhs.searchbookapp.presentation.ui.components.button.BookmarkToggleButton
import com.jhs.searchbookapp.presentation.ui.theme.SearchBookAppTheme

@Composable
internal fun BookCard(
    book: Book,
    modifier: Modifier = Modifier,
    onBookClick: (Book) -> Unit = { }
) {
    if (book.contents.isBlank()) {
        DefaultCard(
            modifier = modifier
        ) {
//            BookCardContent(book = book)
        }
    } else {
        DefaultCard(
            modifier = modifier,
            onClick = { onBookClick(book) }
        ) {
//            BookCardContent(book = book)
            BookCardContent(
                book = book
            )
        }
    }
}

@Composable
fun BookCardContent(
    book: Book,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val bookPrice = if (book.sale_price > 0) book.sale_price else book.price
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(start = 16.dp, end = 18.dp, top = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BookmarkToggleButton(
            bookmarked = book.isBookmarked,
            onClickBookmark = {
                detailViewModel.updateBookmark(
                    book = book,
                    isBookmark = !book.isBookmarked
                )
            }
        )
        NetworkImage(url = book.thumbnail)
        Column(
            modifier = Modifier.padding(start = 20.dp)
        ) {
            Text(
                text = book.title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Text(
                text = book.authors.map { it }.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Text(
                text = String.format("%,d원", bookPrice),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }

//    LaunchedEffect(book.isbn) {
//        detailViewModel.fetchBook(book.isbn)
//    }
}


@Preview
@Composable
private fun BookDetailContentPreview(
    @PreviewParameter(BookDetailContentProvider::class) book: Book,
) {
    SearchBookAppTheme {
        BookCard(book = book)
    }
}