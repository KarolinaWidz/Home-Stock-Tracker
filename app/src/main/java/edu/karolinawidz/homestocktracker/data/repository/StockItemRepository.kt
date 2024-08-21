package edu.karolinawidz.homestocktracker.data.repository

import edu.karolinawidz.homestocktracker.data.local.Item
import kotlinx.coroutines.flow.Flow

interface StockItemRepository {
    suspend fun addItem(item: Item)

    suspend fun deleteItem(item: Item)

    suspend fun updateItem(item: Item)

    fun findItemByName(itemName: String): Flow<Item>

    fun getAllItems(): Flow<List<Item>>

    fun getAllItemsWithCategory(category: String): Flow<List<Item>>
}
