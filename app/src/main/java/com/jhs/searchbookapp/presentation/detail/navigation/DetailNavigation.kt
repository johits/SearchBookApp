package com.jhs.searchbookapp.presentation.detail.navigation


import androidx.navigation.NavController
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


fun NavController.navigateBookDetail(book: String) {
    navigate(DetailRoute.detailRoute(book))
}

object DetailRoute {

    const val ROUTE: String = "detail/{book}"
    fun detailRoute(bookJsonString: String): String {
        return "detail/${
            URLEncoder.encode(
                bookJsonString,
                StandardCharsets.UTF_8.toString()
            )
        }"
    }
}