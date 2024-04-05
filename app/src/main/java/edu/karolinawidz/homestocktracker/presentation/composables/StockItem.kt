package edu.karolinawidz.homestocktracker.presentation.composables

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.karolinawidz.homestocktracker.presentation.theme.HomeStockTrackerTheme


@Composable
fun StockItem(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
    ) {

    }

}

@Preview
@Composable
fun PreviewStockListScreen() {
    HomeStockTrackerTheme {
        StockItem()
    }
}
