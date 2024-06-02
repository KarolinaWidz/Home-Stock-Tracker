package edu.karolinawidz.homestocktracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StockItem::class], version = 1, exportSchema = false)
abstract class StockDatabase : RoomDatabase() {
    abstract fun stockItemDao(): StockItemDao
}