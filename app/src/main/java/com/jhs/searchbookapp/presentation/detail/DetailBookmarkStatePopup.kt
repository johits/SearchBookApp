package com.jhs.searchbookapp.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jhs.searchbookapp.R
import com.jhs.searchbookapp.presentation.ui.theme.HotPink
import com.jhs.searchbookapp.presentation.ui.theme.SearchBookAppTheme


@Composable
internal fun DetailBookmarkStatePopup(bookmarked: Boolean) {
    val messageStringRes by rememberUpdatedState(
        newValue = if (bookmarked) {
            R.string.detail_bookmark_popup
        } else {
            R.string.detail_un_bookmark_popup
        }
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(4.dp),
        color = Color.White
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 15.dp),
            text = stringResource(id = messageStringRes),
            style = SearchBookAppTheme.typography.bodyMediumR,
            color = HotPink
        )
    }
}

@Preview
@Composable
private fun BookmarkStatePopupPreview() {
    Column {
        DetailBookmarkStatePopup(bookmarked = true)
        DetailBookmarkStatePopup(bookmarked = false)
    }
}
