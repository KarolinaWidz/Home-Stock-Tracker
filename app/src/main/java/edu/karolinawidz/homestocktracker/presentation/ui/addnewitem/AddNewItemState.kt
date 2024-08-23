package edu.karolinawidz.homestocktracker.presentation.ui.addnewitem

import edu.karolinawidz.homestocktracker.presentation.ui.common.StockItem

data class AddNewItemState(
    val newItem: StockItem? = null,
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
    val isError: Boolean = false
)
