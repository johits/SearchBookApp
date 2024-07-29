package com.jhs.searchbookapp.data.bookmark.mapper

import com.jhs.searchbookapp.data.core.local.room.entity.BookmarkEntity
import com.jhs.searchbookapp.domain.search.model.Book
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

fun Book.toEntity(): BookmarkEntity {
    return BookmarkEntity(
        authors = authors,
        contents = contents,
        isbn = isbn,
        publisher = publisher,
        price = price,
        sale_price = sale_price,
        thumbnail = URLDecoder.decode(thumbnail, StandardCharsets.UTF_8.toString()),
        url = URLDecoder.decode(url, StandardCharsets.UTF_8.toString()),
        title = title,
        isBookmarked = isBookmarked
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
        thumbnail = URLDecoder.decode(thumbnail, StandardCharsets.UTF_8.toString()),
        url = URLDecoder.decode(url, StandardCharsets.UTF_8.toString()),
        title = title,
        isBookmarked = isBookmarked
    )
}