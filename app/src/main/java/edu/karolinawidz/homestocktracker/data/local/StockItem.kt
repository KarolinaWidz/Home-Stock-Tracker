package edu.karolinawidz.homestocktracker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("item")
data class StockItem(@PrimaryKey val name: String, val quantity: Int, val category: String)
