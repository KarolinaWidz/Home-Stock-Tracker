package edu.karolinawidz.homestocktracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 4, exportSchema = false)
abstract class StockDatabase : RoomDatabase() {
    abstract fun stockItemDao(): StockItemDao
}