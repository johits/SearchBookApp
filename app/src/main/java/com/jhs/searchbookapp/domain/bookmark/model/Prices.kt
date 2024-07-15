package com.jhs.searchbookapp.domain.bookmark.model


data class Prices(
    var lowPrice: String = "",
    var highPrice: String = "",
) {
    fun isNotEmpty(): Boolean {
        return lowPrice.isNotEmpty() && highPrice.isNotEmpty()
    }
}