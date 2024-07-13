package com.jhs.searchbookapp.data.search.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BooksResponse(
    @SerialName("documents")
    val documents: List<BookResponse>,
    @SerialName("meta")
    val meta : MetaResponse
)
