package com.jhs.searchbookapp.presentation.search.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhs.searchbookapp.presentation.search.SearchViewModel
import com.jhs.searchbookapp.presentation.ui.components.SearchItem

@Composable
internal fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val bookState by viewModel.books.collectAsState()


    // 초기에 책을 불러오도록 예시
    LaunchedEffect(Unit) {
        viewModel.getBooks("파과")
    }

    val books = viewModel.books.collectAsState()
    val booksItem by remember { books }
    /**
     * 결제 목록
     */
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ){
        booksItem.let {
            itemsIndexed(booksItem) { _, item ->
                SearchItem(book = item)
            }
        }
    }
}

