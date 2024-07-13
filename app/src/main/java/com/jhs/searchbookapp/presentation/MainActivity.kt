package com.jhs.searchbookapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.jhs.searchbookapp.R
import com.jhs.searchbookapp.presentation.search.SearchViewModel
import com.jhs.searchbookapp.presentation.search.screen.SearchScreen
import com.jhs.searchbookapp.presentation.ui.theme.SearchBookAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel:SearchViewModel by viewModels()
//    init {
//        viewModel.getBooks("파과")
//        Log.d("test","${viewModel.books.value}")
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//    viewModel.getBooks("파과")
        Log.d("test","${viewModel.books.value}")
        setContent {
            SearchBookAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    viewModel.getBooks("파과")
//                    lifecycleScope.launch {
//                        viewModel.books.collect{
//                            Log.e("test", "$it")
//                        }
//                    }

                    SearchScreen()
//                    Greeting("Android")
                }
            }
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    SearchBookAppTheme {
//        SearchScreen()
//    }
//}