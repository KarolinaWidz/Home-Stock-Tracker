package edu.karolinawidz.homestocktracker.presentation.ui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import edu.karolinawidz.homestocktracker.presentation.ui.theme.PaddingLarge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SwipeToDeleteContainer(
    item: T,
    onDelete: (T) -> Unit,
    enableDismissFromStartToEnd: Boolean = true,
    enableDismissFromEndToStart: Boolean = true,
    content: @Composable (T) -> Unit
) {
    val state = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.EndToStart) {
                onDelete(item)
                true
            } else {
                false
            }
        },
        positionalThreshold = { it * .25f }
    )
    SwipeToDismissBox(
        state = state,
        backgroundContent = { DeleteBackground(swipeDismissState = state) },
        enableDismissFromStartToEnd = enableDismissFromStartToEnd,
        enableDismissFromEndToStart = enableDismissFromEndToStart,
        content = { content(item) })


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DeleteBackground(
    swipeDismissState: SwipeToDismissBoxState,
    modifier: Modifier = Modifier
) {
    val color = if (swipeDismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart)
        MaterialTheme.colorScheme.error
    else Color.Transparent
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color)
            .padding(PaddingLarge),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete item",
            tint = MaterialTheme.colorScheme.onError
        )
    }
}
