package com.jhs.searchbookapp.presentation.search.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jhs.searchbookapp.domain.search.model.Book
import com.jhs.searchbookapp.presentation.detail.navigation.DetailRoute
import com.jhs.searchbookapp.presentation.detail.screen.BookDetailScreen
import com.jhs.searchbookapp.presentation.search.screen.SearchRoute

fun NavController.navigateSearch(navOptions: NavOptions) {
    navigate(SearchRoute.ROUTE, navOptions)
}

fun NavGraphBuilder.searchNavGraph(
    padding: PaddingValues,
    onBackClick: () -> Unit,
    onBookClick: (Book) -> Unit,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
    composable(SearchRoute.ROUTE) {
        SearchRoute(
            padding = padding,
            onBackClick = onBackClick,
            onBookClick = onBookClick,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }

    composable(
        route = DetailRoute.ROUTE,
        arguments = listOf(navArgument("id") { type = NavType.StringType })
    ) { navBackStackEntry ->
        val bookId = navBackStackEntry.arguments?.getString("id") ?: ""
        BookDetailScreen(
            bookId = bookId,
            onBackClick = onBackClick
        )
    }
}

object SearchRoute {

    const val ROUTE: String = "search"
}
