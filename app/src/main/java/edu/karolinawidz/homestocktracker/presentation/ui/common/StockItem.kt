package edu.karolinawidz.homestocktracker.presentation.ui.common

import edu.karolinawidz.homestocktracker.data.local.Item
import kotlinx.collections.immutable.toImmutableList

data class StockItem(
    val name: String = "",
    val quantity: Int = 0,
    val category: Category = Category.UNKNOWN
)

fun Item.toStockItem() = StockItem(
    name = this.name,
    quantity = this.quantity,
    category = Category.findByName(this.category)
)

fun List<Item>.toStockItems() = this.map { item -> item.toStockItem() }.toImmutableList()


