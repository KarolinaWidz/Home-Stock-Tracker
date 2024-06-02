package edu.karolinawidz.homestocktracker.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StockItemDao {
    @Insert
    fun insert(item: StockItem)

    @Delete
    fun delete(item: StockItem)

    @Update
    fun update(item: StockItem)

    @Query("SELECT * FROM item WHERE name=:itemName LIMIT 1")
    fun findByName(itemName: String): Flow<StockItem>

    @Query("SELECT * FROM item")
    fun getAll():Flow<List<StockItem>>

    @Query("SELECT * FROM item WHERE category=:category")
    fun getAllWithCategory(category: String):Flow<List<StockItem>>

}