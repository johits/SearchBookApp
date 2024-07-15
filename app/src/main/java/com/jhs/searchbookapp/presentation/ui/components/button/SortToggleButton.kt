package com.jhs.searchbookapp.presentation.ui.components.button

import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.jhs.searchbookapp.R

@Composable
fun SortToggleButton(
    clickState: Boolean,
    onClick: (Boolean) -> Unit
) {
    IconToggleButton(
        checked = clickState,
        onCheckedChange = onClick
    ) {
        Icon(
            painter =
            if (clickState) {
                painterResource(id = R.drawable.ic_sort_down)
            } else {
                painterResource(id = R.drawable.ic_sort_up)
            },
            contentDescription = null
        )
    }
}