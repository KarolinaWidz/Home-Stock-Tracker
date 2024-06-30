package edu.karolinawidz.homestocktracker.presentation.ui.stocklist.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import edu.karolinawidz.homestocktracker.R
import edu.karolinawidz.homestocktracker.presentation.ui.theme.HomeStockTrackerTheme

@Composable
fun AddItemFab(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}

) {
    FloatingActionButton(modifier = modifier, onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_new_item)
        )
    }
}

@PreviewLightDark
@Composable
private fun PreviewStockListScreen() {
    HomeStockTrackerTheme {
        AddItemFab()
    }
}