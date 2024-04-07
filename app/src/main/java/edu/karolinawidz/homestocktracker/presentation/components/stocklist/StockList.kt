package edu.karolinawidz.homestocktracker.presentation.components.stocklist

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import edu.karolinawidz.homestocktracker.presentation.components.stockitem.Category
import edu.karolinawidz.homestocktracker.presentation.components.stockitem.StockItem
import edu.karolinawidz.homestocktracker.presentation.components.stockitem.StockItemCard
import edu.karolinawidz.homestocktracker.presentation.theme.HomeStockTrackerTheme
import edu.karolinawidz.homestocktracker.presentation.theme.PaddingSmall
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf


@Composable
internal fun StockList(
    modifier: Modifier = Modifier,
    stockItems: ImmutableList<StockItem> = persistentListOf()
) {
    LazyColumn(modifier = modifier.padding(PaddingSmall)) {
        items(stockItems) { stockItem ->
            StockItemCard(stockItem = stockItem)
        }
    }
}

@PreviewLightDark

@Composable
private fun PreviewStockListScreen() {
    HomeStockTrackerTheme {
        val itemList = persistentListOf(
            StockItem("Soap", 1, Category.Cosmetics),
            StockItem("Butter", 2, Category.Food),
            StockItem("Cola", 4, Category.Food),
            StockItem("Aspirin", 1, Category.Medicine)
        )
        StockList(stockItems = itemList)
    }
}


