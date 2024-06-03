package edu.karolinawidz.homestocktracker.presentation.ui.state

import edu.karolinawidz.homestocktracker.presentation.ui.components.stockitem.StockItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class HomeStockState(
    val isLoading: Boolean = false,
    val stockItems: ImmutableList<StockItem> = persistentListOf(),
)
