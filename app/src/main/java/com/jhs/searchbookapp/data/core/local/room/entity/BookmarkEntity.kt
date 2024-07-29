package com.jhs.searchbookapp.data.core.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val title: String, //도서 제목
    val authors: List<String>, //저자
    val contents: String, //내용
    @ColumnInfo(name = "isbn") val isbn: String, //ISBN10(10자리) 또는 ISBN13(13자리) 형식의 국제 표준 도서번호
    val publisher: String, //출판사
    val price: Int, //도서 정가
    val sale_price: Int, //도서 판매가
    val thumbnail: String, //도서 표지 미리보기 URL
    val url: String, //고유값으로 사용하기 위함
    val isBookmarked: Boolean //즐겨찾기 여부
) {
    override fun toString(): String {
        return "id = $id\n" +
                "title = $title\n" +
                "authors = ${authors.map { it }}\n" +
                "contents = $contents\n" +
                "isbn = $isbn\n" +
                "publisher = $publisher\n" +
                "price = $price\n" +
                "sale_price = $sale_price\n" +
                "thumbnail = $thumbnail\n" +
                "url = $url\n" +
                "isBookmarked = $isBookmarked"
    }
}