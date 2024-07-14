package com.jhs.searchbookapp.presentation.bookmark.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.jhs.searchbookapp.domain.search.model.Book
import com.jhs.searchbookapp.presentation.bookmark.screen.BookmarkRoute

fun NavController.navigateBookmark(navOptions: NavOptions) {
    navigate(BookmarkRoute.ROUTE, navOptions)
}

fun NavGraphBuilder.bookmarkNavGraph(
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
    onBookClick: (Book) -> Unit
) {
    composable(route = BookmarkRoute.ROUTE) {
        BookmarkRoute(onShowErrorSnackBar,onBookClick)
    }
}

object BookmarkRoute {

    const val ROUTE = "bookmark"
}
