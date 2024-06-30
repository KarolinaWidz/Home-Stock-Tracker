package edu.karolinawidz.homestocktracker.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.karolinawidz.homestocktracker.presentation.ui.screen.AddNewItemScreen
import edu.karolinawidz.homestocktracker.presentation.ui.screen.StockListScreen

@Composable
fun HomeStockNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Destination.Home.name
) {
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier)
    {
        composable(route = Destination.Home.name)
        {
            StockListScreen(
                onAddItemClicked = { navController.navigate(route = Destination.NewItem.name) },
            )
        }
        composable(route = Destination.NewItem.name) {
            AddNewItemScreen(onBackClicked = { navController.navigateUp() })
        }
    }
}