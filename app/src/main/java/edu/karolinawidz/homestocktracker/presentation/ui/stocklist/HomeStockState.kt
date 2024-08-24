package edu.karolinawidz.homestocktracker.presentation.ui.stocklist

import edu.karolinawidz.homestocktracker.presentation.ui.common.StockItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class HomeStockState(
    val isLoading: Boolean = true,
    val isOrderAscending: Boolean = true,
    val stockItems: ImmutableList<StockItem> = persistentListOf(),
)
