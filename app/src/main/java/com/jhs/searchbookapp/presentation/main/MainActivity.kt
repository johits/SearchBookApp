package com.jhs.searchbookapp.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jhs.searchbookapp.presentation.ui.theme.SearchBookAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val bookIdFromWidget: MutableStateFlow<String?> = MutableStateFlow(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {

            val navigator: MainNavigator = rememberMainNavigator()
            val bookId = bookIdFromWidget.collectAsStateWithLifecycle().value

//            LaunchedEffect(bookId) {
//                bookId?.let {
//                    navigator.navigateBookDetail(it.toString())
//                }
//            }

            SearchBookAppTheme() {
                MainScreen(
                    navigator = navigator
                )
            }
        }
    }
}
