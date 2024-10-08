package com.jhs.searchbookapp.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jhs.searchbookapp.presentation.ui.theme.SearchBookAppTheme

@Composable
fun DefaultCard(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    color: Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit,
) {
    Surface(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.fillMaxWidth(),
        color = color,
        shape = RoundedCornerShape(32.dp),
        shadowElevation = 2.dp,
        content = content,
    )
}

@Composable
@Preview
private fun DefaultCardPreview() {
    SearchBookAppTheme {
        DefaultCard(modifier = Modifier.size(320.dp, 160.dp), content = { })
    }
}