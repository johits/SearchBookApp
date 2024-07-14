package com.jhs.searchbookapp.presentation.detail

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
sealed interface DetailEffect {

    @Immutable
    data object Idle : DetailEffect

    @Immutable
    data class ShowToastForBookmarkState(val bookmarked: Boolean) : DetailEffect
}