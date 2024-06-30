package edu.karolinawidz.homestocktracker.presentation.ui.components.common.stockitem

import edu.karolinawidz.homestocktracker.data.local.Item
import kotlinx.collections.immutable.toImmutableList

data class StockItem(val name: String, val quantity: Int, val category: Category)

fun Item.toStockItem() = StockItem(
    name = this.name,
    quantity = this.quantity,
    category = Category.findByName(this.category)
)

fun List<Item>.toStockItems() = this.map { item -> item.toStockItem() }.toImmutableList()


