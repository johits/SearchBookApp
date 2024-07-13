package com.jhs.searchbookapp.presentation.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import com.jhs.searchbookapp.domain.search.model.Book
import com.jhs.searchbookapp.presentation.ui.theme.SearchBookAppTheme
import coil.compose.rememberAsyncImagePainter
import coil.size.Scale
//@Composable
//internal fun BookmarkCard(
//    modifier: Modifier = Modifier,
//    tagLabel: String,
//    title: String,
//    speaker: String,
//) {
//    Column(
//        modifier = modifier
//            .fillMaxWidth()
//            .background(
//                color = MaterialTheme.colorScheme.primaryContainer,
//                shape = RoundedCornerShape(8.dp)
//            )
//            .padding(start = 16.dp, end = 18.dp, top = 16.dp, bottom = 16.dp),
//        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
//    ) {
//        Row(
//            modifier = Modifier,
//            horizontalArrangement = Arrangement.spacedBy(8.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Box(
//                modifier = Modifier
//                    .background(color = Purple01, shape = CircleShape)
//                    .size(12.dp)
//            )
//            Text(
//                modifier = Modifier.weight(1F),
//                text = tagLabel,
//                style = SearchBookTheme.typography.labelSmallM,
//                color = MaterialTheme.colorScheme.onSurfaceVariant
//            )
//        }
//
//        Text(
//            text = title,
//            style = SearchBookTheme.typography.titleSmallB,
//            color = MaterialTheme.colorScheme.onSecondaryContainer
//        )
//
//        Text(
//            text = speaker,
//            style = SearchBookTheme.typography.labelSmallM,
//            color = MaterialTheme.colorScheme.onSecondaryContainer
//        )
//    }
//}
//
//
//@Preview
//@Composable
//private fun BookmarkCardPreview(
//) {
//    SearchBookTheme {
//        Box {
//            BookmarkCard(
//                tagLabel = "효율적인 코드 베이스",
//                title = "Jetpack Compose에 있는 것, 없는것",
//                speaker = "홍길동"
//            )
//        }
//    }
//}


@Composable
fun SearchItem(
    book: Book
) {
//    Column(
//        modifier = modifier
//            .fillMaxWidth()
//            .background(
//                color = MaterialTheme.colorScheme.primaryContainer,
//                shape = RoundedCornerShape(8.dp)
//            )
//            .padding(start = 16.dp, end = 18.dp, top = 16.dp, bottom = 16.dp),
//        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
//    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(start = 16.dp, end = 18.dp, top = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BookThumbnail(url = book.thumbnail)

        Column(
            modifier = Modifier.padding(start = 20.dp)
        ) {
            Text(
                text = book.title,
                style = typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Text(
                text = book.authors.map { it }.toString(),
                style = typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Text(
                text = book.sale_price.toString(),
                style = typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }

    }
//    }

}


@Composable
fun BookThumbnail(
    url: String
) {
    Log.d("tkwls", "들어온 사진:$url")
    Card {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest
                    .Builder(LocalContext.current)
                    .data(data = url)
                    .apply {
                        crossfade(true)
                        scale(Scale.FILL)
                    }.build()
            ),
            modifier = Modifier
                .width(100.dp)
                .height(170.dp),
            contentScale = ContentScale.FillHeight,
            contentDescription = "Book Image"
        )
    }
}


@Preview
@Composable
fun DefaultCardPreview() {
    SearchBookAppTheme {
        SearchItem(
            book = Book(
                authors = listOf("기시미 이치로", "고가 후미타케"),
                contents = "인간은 변할 수 있고, 누구나 행복해 질 수 있다. 단 그러기 위해서는 ‘용기’가 필요하다고 말한 철학자가 있다. 바로 프로이트, 융과 함께 ‘심리학의 3대 거장’으로 일컬어지고 있는 알프레드 아들러다. 『미움받을 용기』는 아들러 심리학에 관한 일본의 1인자 철학자 기시미 이치로와 베스트셀러 작가인 고가 후미타케의 저서로, 아들러의 심리학을 ‘대화체’로 쉽고 맛깔나게 정리하고 있다. 아들러 심리학을 공부한 철학자와 세상에 부정적이고 열등감 많은",
                isbn = "8996991341 9788996991342",
                sale_price = 14900,
                publisher = "인플루엔셜",
                thumbnail = "https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F1467038",
                title = "미움받을 용기"
            )
        )
    }
}