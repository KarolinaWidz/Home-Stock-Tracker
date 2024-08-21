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
    suspend fun insert(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Update
    suspend fun update(item: Item)

    @Query("SELECT * FROM item WHERE name=:itemName LIMIT 1")
    fun findByName(itemName: String): Flow<Item>

    @Query("SELECT * FROM item")
    fun getAll():Flow<List<Item>>

    @Query("SELECT * FROM item WHERE category=:category")
    fun getAllWithCategory(category: String):Flow<List<Item>>

}