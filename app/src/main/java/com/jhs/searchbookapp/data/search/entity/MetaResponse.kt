package com.jhs.searchbookapp.data.search.entity

import kotlinx.serialization.Serializable

@Serializable
data class MetaResponse(
    val total_count : Int,    // 검색된 문서 수
    val pageable_count : Int,    // 중복된 문서를 제외하고, 처음부터 요청 페이지까지의 노출 가능 문서 수
    val is_end : Boolean    // 현재 페이지가 마지막 페이지인지 여부
)