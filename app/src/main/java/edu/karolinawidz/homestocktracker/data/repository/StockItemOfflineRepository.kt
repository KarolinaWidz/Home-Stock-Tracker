package edu.karolinawidz.homestocktracker.data.repository

import edu.karolinawidz.homestocktracker.data.local.Item
import edu.karolinawidz.homestocktracker.data.local.StockItemDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StockItemOfflineRepository @Inject constructor(private val dao: StockItemDao) :
    StockItemRepository {
    override suspend fun addItem(item: Item) {
        dao.insert(item)
    }

    override suspend fun deleteItem(item: Item) {
        dao.delete(item)
    }

    override suspend fun updateItem(item: Item) {
        dao.update(item)
    }

    override fun findItemsByName(itemName: String): Flow<List<Item>> {
        return dao.findAllWithName(itemName)
    }

    override fun getAllItems(): Flow<List<Item>> {
        return dao.getAll()
    }
}