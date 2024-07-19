package com.jhs.searchbookapp.presentation.search.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.jhs.searchbookapp.domain.search.model.Book
import com.jhs.searchbookapp.presentation.detail.navigation.DetailRoute
import com.jhs.searchbookapp.presentation.detail.screen.BookDetailScreen
import com.jhs.searchbookapp.presentation.search.screen.SearchRoute
import java.net.URLDecoder
import java.nio.charset.StandardCharsets


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
            onBookClick = onBookClick,
            onShowErrorSnackBar = onShowErrorSnackBar
        )
    }

    composable(
        route = DetailRoute.ROUTE,
        arguments = listOf(navArgument("book") { type = NavType.StringType })
    ) { navBackStackEntry ->
        val bookDataJson = navBackStackEntry.arguments?.getString("book")
        val book = URLDecoder.decode(bookDataJson, StandardCharsets.UTF_8.toString())
        val bookData = Gson().fromJson(book, Book::class.java)

        BookDetailScreen(
            book = bookData,
            onBackClick = onBackClick
        )

    }
}

object SearchRoute {

    const val ROUTE: String = "search"
}
