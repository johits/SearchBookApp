package com.jhs.searchbookapp.domain.search.model

data class Book(
    val title: String, //도서 제목
    val authors: List<String>, //저자
    val contents: String, //내용
    val isbn: String, //ISBN10(10자리) 또는 ISBN13(13자리) 형식의 국제 표준 도서번호
    val publisher: String, //출판사
    val price: Int, //도서 정가
    val sale_price: Int, //도서 판매가
    val thumbnail: String, //도서 표지 미리보기 URL
    val isBookmarked: Boolean //즐겨찾기 여부
)