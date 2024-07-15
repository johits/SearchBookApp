package com.jhs.searchbookapp.data.search.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaResponse(
    @SerialName("total_count")
    val totalCount: Int,    // 검색된 문서 수
    @SerialName("pageable_count")
    val pageableCount: Int,    // 중복된 문서를 제외하고, 처음부터 요청 페이지까지의 노출 가능 문서 수
    @SerialName("is_end")
    val isEnd: Boolean    // 현재 페이지가 마지막 페이지인지 여부
)