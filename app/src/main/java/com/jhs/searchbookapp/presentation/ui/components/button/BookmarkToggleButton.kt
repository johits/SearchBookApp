package com.jhs.searchbookapp.presentation.ui.components.button

import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.jhs.searchbookapp.R

@Composable
fun BookmarkToggleButton(
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