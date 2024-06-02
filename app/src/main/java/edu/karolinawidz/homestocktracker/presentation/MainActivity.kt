package edu.karolinawidz.homestocktracker.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import edu.karolinawidz.homestocktracker.presentation.components.stockitem.Category
import edu.karolinawidz.homestocktracker.presentation.components.stockitem.StockItem
import edu.karolinawidz.homestocktracker.presentation.theme.HomeStockTrackerTheme
import kotlinx.collections.immutable.persistentListOf

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeStockTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val itemList = persistentListOf(
                        StockItem("Soap", 1, Category.Cosmetics),
                        StockItem("Butter", 2, Category.Food),
                        StockItem("Cola", 4, Category.Food),
                        StockItem("Aspirin", 1, Category.Medicine),
                        StockItem("Dog food", 1, Category.Pets),
                        StockItem("Oil", 2, Category.Car),
                        StockItem("Brush", 4, Category.Household),
                        StockItem("Mirror", 1, Category.Others),
                        StockItem("Soap", 1, Category.Cosmetics),
                        StockItem("Butter", 2, Category.Food),
                        StockItem("Cola", 4, Category.Food),
                        StockItem("Aspirin", 1, Category.Medicine)
                    )
                    StockListScreen(stockItems = itemList)
                }
            }
        }
    }
}