package com.jhs.searchbookapp.presentation.main

import com.jhs.searchbookapp.R
import com.jhs.searchbookapp.presentation.bookmark.navigation.BookmarkRoute
import com.jhs.searchbookapp.presentation.search.navigation.SearchRoute


internal enum class MainTab(
    val iconResId: Int,
    internal val contentDescription: String,
    val route: String,
) {
    SEARCH(
        iconResId = R.drawable.ic_session_bookmark,
        contentDescription = "도서 검색",
        SearchRoute.ROUTE
    ),
    BOOKMARK(
        iconResId = R.drawable.ic_session_bookmark_filled,
        contentDescription = "북마크",
        BookmarkRoute.ROUTE
    );

    companion object {

        operator fun contains(route: String): Boolean {
            return entries.map { it.route }.contains(route)
        }

        fun find(route: String): MainTab? {
            return entries.find { it.route == route }
        }
    }
}
