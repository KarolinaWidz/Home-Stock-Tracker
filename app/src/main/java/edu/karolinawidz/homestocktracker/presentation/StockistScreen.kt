package edu.karolinawidz.homestocktracker.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import edu.karolinawidz.homestocktracker.R
import edu.karolinawidz.homestocktracker.presentation.components.common.TopBar

import edu.karolinawidz.homestocktracker.presentation.components.stockitem.Category
import edu.karolinawidz.homestocktracker.presentation.components.stockitem.StockItem
import edu.karolinawidz.homestocktracker.presentation.components.stocklist.StockList
import edu.karolinawidz.homestocktracker.presentation.theme.HomeStockTrackerTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun StockListScreen(
    modifier: Modifier = Modifier,
    stockItems: ImmutableList<StockItem> = persistentListOf()
) {
    val appTitle = stringResource(id = R.string.app_name)
    var isSearchActive by rememberSaveable { mutableStateOf(false) }
    Scaffold(modifier = modifier,
        topBar = {
            TopBar(
                title = appTitle,
                isSearchActive = isSearchActive,
                onQueryChanged = { TODO() },
                onSearch = { TODO() },
                onActiveChanged = { isSearchActive = it })
        }) { paddingValues ->
        StockList(stockItems = stockItems, modifier = modifier.padding(paddingValues))
    }
}

@PreviewLightDark
@PreviewDynamicColors
@Composable
private fun PreviewStockListScreen() {
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