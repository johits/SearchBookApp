package com.jhs.searchbookapp.data.bookmark.mapper

import com.jhs.searchbookapp.data.core.local.room.entity.BookmarkEntity
import com.jhs.searchbookapp.domain.search.model.Book

fun Book.toEntity(): BookmarkEntity {
    return BookmarkEntity(
        authors = authors,
        contents = contents,
        isbn = isbn,
        publisher = publisher,
        price = price,
        sale_price = sale_price,
        thumbnail = thumbnail,
        title = title,
        isBookmarked = false
    )
}
fun BookmarkEntity.toModel(): Book {
    return Book(
        authors = authors,
        contents = contents,
        isbn = isbn,
        publisher = publisher,
        price = price,
        sale_price = sale_price,
        thumbnail = thumbnail,
        title = title,
        isBookmarked = false
    )
}