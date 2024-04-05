package edu.karolinawidz.homestocktracker.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.karolinawidz.homestocktracker.presentation.theme.HomeStockTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockListScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier) { paddingValues ->
        LazyColumn(contentPadding = paddingValues ){

        }
    }

}

@Preview
@Composable
fun PreviewStockListScreen(){
    HomeStockTrackerTheme {
        StockListScreen()
    }
}
