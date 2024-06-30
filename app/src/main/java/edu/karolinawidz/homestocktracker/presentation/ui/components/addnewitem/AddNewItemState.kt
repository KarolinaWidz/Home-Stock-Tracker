package edu.karolinawidz.homestocktracker.presentation.ui.components.addnewitem

import edu.karolinawidz.homestocktracker.presentation.ui.components.common.stockitem.StockItem

data class AddNewItemState(
    val newItem: StockItem? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
