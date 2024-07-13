package com.jhs.searchbookapp.data.search.mapper

import com.jhs.searchbookapp.data.search.entity.BookResponse
import com.jhs.searchbookapp.domain.search.model.Book

fun BookResponse.toModel(): Book {
    return Book(
        authors = authors,
        contents = contents,
        isbn = isbn,
        publisher = publisher,
        sale_price = sale_price,
        thumbnail = thumbnail,
        title = title,
    )
}