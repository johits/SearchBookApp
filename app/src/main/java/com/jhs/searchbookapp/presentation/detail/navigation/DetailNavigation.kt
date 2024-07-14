package com.jhs.searchbookapp.presentation.detail.navigation

import androidx.navigation.NavController

fun NavController.navigateBookDetail(bookId: String) {
    navigate(DetailRoute.detailRoute(bookId))
}

object DetailRoute {

    const val ROUTE: String = "detail/{id}"
    fun detailRoute(bookId: String): String = "detail/$bookId"
}