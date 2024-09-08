package edu.karolinawidz.homestocktracker.presentation.ui.stocklist

import edu.karolinawidz.homestocktracker.presentation.ui.common.StockItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class StockState(
    val isLoading: Boolean = true,
    val isOrderAscending: Boolean = true,
    val stockItems: ImmutableList<StockItem> = persistentListOf(),
    val searchQuery: String = "",
    val isSearchActive: Boolean = false,
    val searchResult: ImmutableList<StockItem> = persistentListOf(),
)
