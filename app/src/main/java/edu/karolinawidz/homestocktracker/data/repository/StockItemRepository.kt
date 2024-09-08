package edu.karolinawidz.homestocktracker.data.repository

import edu.karolinawidz.homestocktracker.data.local.Item
import kotlinx.coroutines.flow.Flow

interface StockItemRepository {
    suspend fun addItem(item: Item)

    suspend fun deleteItem(item: Item)

    suspend fun updateItem(item: Item)

    fun findItemsByName(itemName: String): Flow<List<Item>>

    fun getAllItems(): Flow<List<Item>>
}
