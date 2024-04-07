package edu.karolinawidz.homestocktracker.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import edu.karolinawidz.homestocktracker.presentation.composables.StockItemCard
import edu.karolinawidz.homestocktracker.presentation.stockitem.Category
import edu.karolinawidz.homestocktracker.presentation.stockitem.StockItem
import edu.karolinawidz.homestocktracker.presentation.theme.HomeStockTrackerTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun StockListScreen(
    modifier: Modifier = Modifier,
    stockItems: ImmutableList<StockItem> = persistentListOf()
) {
    Scaffold(modifier = modifier) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            items(stockItems) { stockItem ->
                StockItemCard(stockItem = stockItem)
            }
        }
    }
}

@PreviewLightDark
@PreviewDynamicColors
@Composable
fun PreviewStockListScreen() {
    HomeStockTrackerTheme {
        val itemList = persistentListOf(
            StockItem("Soap", 1, Category.Cosmetics),
            StockItem("Butter", 2, Category.Food),
            StockItem("Cola", 4, Category.Food),
            StockItem("Aspirin", 1, Category.Medicine)
        )
        StockListScreen(stockItems = itemList)
    }
}
