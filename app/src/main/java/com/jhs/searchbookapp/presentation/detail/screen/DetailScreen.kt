package com.jhs.searchbookapp.presentation.detail.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.jhs.searchbookapp.R
import com.jhs.searchbookapp.domain.search.model.Book
import com.jhs.searchbookapp.presentation.detail.DetailBookmarkStatePopup
import com.jhs.searchbookapp.presentation.detail.DetailEffect
import com.jhs.searchbookapp.presentation.detail.DetailUiState
import com.jhs.searchbookapp.presentation.detail.DetailViewModel
import com.jhs.searchbookapp.presentation.search.SearchViewModel
import com.jhs.searchbookapp.presentation.search.screen.SearchScreen
import com.jhs.searchbookapp.presentation.ui.components.SearchBookAppTopAppBar
import com.jhs.searchbookapp.presentation.ui.components.TopAppBarNavigationType
import com.jhs.searchbookapp.presentation.ui.theme.SearchBookAppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest


@Composable
internal fun DetailRoute(
    padding: PaddingValues,
    bookId: String,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel(),
) {
//    LaunchedEffect(true) {
//        viewModel.errorFlow.collectLatest { throwable -> onShowErrorSnackBar(throwable) }
//    }

    BookDetailScreen(
        bookId = bookId,
        onBackClick = onBackClick
    )
}

@Composable
internal fun BookDetailScreen(
    bookId: String,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()
    val detailUiState by viewModel.detailUiState.collectAsStateWithLifecycle()
    val effect by viewModel.detailUiEffect.collectAsStateWithLifecycle()

    val context = LocalContext.current

//    LaunchedEffect(effect) {
//        if (effect is DetailEffect.ShowToastForBookmarkState) {
//            sendWidgetUpdateCommand(context)
//        }
//    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceDim)
            .systemBarsPadding()
            .verticalScroll(scrollState),
    ) {
        BookDetailTopAppBar(
            bookmarked = (detailUiState as? DetailUiState.Success)?.bookmarked ?: false,
            onClickBookmark = { viewModel.toggleBookmark() },
            onBackClick = onBackClick
        )
        Box {
            BookDetailContent(uiState = detailUiState)
            if (effect is DetailEffect.ShowToastForBookmarkState) {
                DetailBookmarkStatePopup(
                    bookmarked = (effect as DetailEffect.ShowToastForBookmarkState).bookmarked
                )
            }
        }
    }

    LaunchedEffect(bookId) {
        viewModel.fetchSession(bookId)
    }

    LaunchedEffect(effect) {
        if (effect is DetailEffect.ShowToastForBookmarkState) {
            delay(1000L)
            viewModel.hidePopup()
        }
    }
}

@Composable
private fun BookDetailTopAppBar(
    bookmarked: Boolean,
    onClickBookmark: (Boolean) -> Unit,
    onBackClick: () -> Unit,
) {
    SearchBookAppTopAppBar(
        titleRes = R.string.title_view_detail,
        navigationIconContentDescription = null,
        navigationType = TopAppBarNavigationType.Back,
        actionButtons = {
            BookmarkToggleButton(
                bookmarked = bookmarked,
                onClickBookmark = onClickBookmark
            )
        },
        onNavigationClick = onBackClick,
    )
}

@Composable
private fun BookOverview(content: String) {
    Text(
        text = content,
        style = SearchBookAppTheme.typography.titleSmallR140,
        color = MaterialTheme.colorScheme.onSecondaryContainer
    )
}

@Composable
private fun BookmarkToggleButton(
    bookmarked: Boolean,
    onClickBookmark: (Boolean) -> Unit,
) {
    IconToggleButton(
        checked = bookmarked,
        onCheckedChange = onClickBookmark
    ) {
        Icon(
            painter =
            if (bookmarked) {
                painterResource(id = R.drawable.ic_session_bookmark_filled)
            } else {
                painterResource(id = R.drawable.ic_session_bookmark)
            },
            contentDescription = null
        )
    }
}

@Composable
private fun BookDetailTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier.padding(end = 64.dp),
        text = title,
        style = SearchBookAppTheme.typography.headlineMediumB,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
    )
}

@Composable
private fun BookDetailIntroduction(
    book: Book,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(color = Color.White)
    ) {
        ThumbnailImage(
            book.thumbnail
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.detail_authors),
            style = SearchBookAppTheme.typography.labelLargeM,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )
        Text(
            text = book.authors.map { it }.toString(),
            style = SearchBookAppTheme.typography.titleMediumB,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.detail_publisher),
            style = SearchBookAppTheme.typography.labelLargeM,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )
        Text(
            text = book.publisher,
            style = SearchBookAppTheme.typography.titleMediumB,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.detail_price),
            style = SearchBookAppTheme.typography.labelLargeM,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )
        val bookPrice = if (book.sale_price > 0) book.sale_price else book.price
        Text(
            text = String.format("%,d원", bookPrice),
            style = SearchBookAppTheme.typography.titleMediumB,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(id = R.string.detail_introduction),
            style = SearchBookAppTheme.typography.labelLargeM,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )
        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outline)
        Spacer(modifier = Modifier.height(5.dp))

        if (book.contents.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            BookOverview(content = book.contents)
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
private fun BookDetailContent(uiState: DetailUiState) {
    when (uiState) {
        is DetailUiState.Loading -> DetailLoading()
        is DetailUiState.Success -> BookDetailContent(uiState.book)
    }
}

@Composable
private fun DetailLoading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
@Composable
private fun BookDetailContent(book: Book) {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        BookDetailTitle(title = book.title, modifier = Modifier.padding(top = 8.dp))
        Spacer(modifier = Modifier.height(8.dp))

        BookDetailIntroduction(book = book)
    }
}

@Composable
fun ThumbnailImage(
    thumbnailUrl: String
) {
    Card {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest
                    .Builder(LocalContext.current)
                    .data(data = thumbnailUrl)
                    .apply {
                        crossfade(true)
                        scale(Scale.FILL)
                    }.build()
            ),
            modifier = Modifier
                .width(180.dp)
                .height(250.dp),
            contentScale = ContentScale.FillHeight,
            contentDescription = "Movie Poster Image"
        )
    }
}


@Preview
@Composable
private fun SessionDetailTopAppBarPreview() {
    SearchBookAppTheme {
        var state by remember { mutableStateOf(false) }
        BookDetailTopAppBar(
            bookmarked = state,
            onClickBookmark = {
                state = it
            }
        ) { }
    }
}

class BookDetailContentProvider : PreviewParameterProvider<Book> {
    override val values: Sequence<Book> = sequenceOf(
        SampleBookNoContent
    )
}

private val SampleBookNoContent = Book(
    authors = listOf("기시미 이치로", "고가 후미타케"),
    contents = "인간은 변할 수 있고, 누구나 행복해 질 수 있다. 단 그러기 위해서는 ‘용기’가 필요하다고 말한 철학자가 있다. 바로 프로이트, 융과 함께 ‘심리학의 3대 거장’으로 일컬어지고 있는 알프레드 아들러다. 『미움받을 용기』는 아들러 심리학에 관한 일본의 1인자 철학자 기시미 이치로와 베스트셀러 작가인 고가 후미타케의 저서로, 아들러의 심리학을 ‘대화체’로 쉽고 맛깔나게 정리하고 있다. 아들러 심리학을 공부한 철학자와 세상에 부정적이고 열등감 많은",
    isbn = "8996991341 9788996991342",
    price = 14900,
    sale_price = 13410,
    publisher = "인플루엔셜",
    thumbnail = "https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F1467038",
    title = "미움받을 용기",
    isBookmarked = false
)

@Preview
@Composable
private fun BookDetailTitlePreview() {
    SearchBookAppTheme {
        BookDetailTitle(title = SampleBookNoContent.title)
    }
}

@Preview
@Composable
private fun BookDetailSpeakerPreview() {
    SearchBookAppTheme {
        BookDetailIntroduction(SampleBookNoContent)
    }
}

@Preview
@Composable
private fun BookDetailContentPreview(
    @PreviewParameter(BookDetailContentProvider::class) book: Book,
) {
    SearchBookAppTheme {
        BookDetailContent(book = book)
    }
}