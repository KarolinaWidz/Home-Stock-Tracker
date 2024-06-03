package edu.karolinawidz.homestocktracker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(@PrimaryKey val name: String, val quantity: Int, val category: String)
