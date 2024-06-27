package edu.karolinawidz.homestocktracker.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import edu.karolinawidz.homestocktracker.presentation.ui.navigation.HomeStockNavigation

@Composable
fun HomeStockApp() {
    val navController = rememberNavController()
    HomeStockNavigation(navController = navController)
}