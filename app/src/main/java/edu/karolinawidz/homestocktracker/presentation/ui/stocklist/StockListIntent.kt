package edu.karolinawidz.homestocktracker.presentation.ui.stocklist

import edu.karolinawidz.homestocktracker.presentation.ui.common.StockItem

sealed interface StockListIntent {
    data object LoadStock : StockListIntent
    data class IncreaseAmountOfItem(val item: StockItem) : StockListIntent
    data class DecreaseAmountOfItem(val item: StockItem) : StockListIntent
    data class DeleteItem(val item: StockItem) : StockListIntent
    data object ChangeOrder : StockListIntent
}