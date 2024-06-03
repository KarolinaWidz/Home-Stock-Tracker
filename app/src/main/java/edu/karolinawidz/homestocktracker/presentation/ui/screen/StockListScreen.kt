package edu.karolinawidz.homestocktracker.presentation.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import edu.karolinawidz.homestocktracker.R
import edu.karolinawidz.homestocktracker.presentation.ui.components.common.TopBar
import edu.karolinawidz.homestocktracker.presentation.ui.components.stocklist.StockList
import edu.karolinawidz.homestocktracker.presentation.ui.theme.HomeStockTrackerTheme
import edu.karolinawidz.homestocktracker.presentation.ui.viewmodel.StockListViewModel

@Composable
fun StockListScreen(
    modifier: Modifier = Modifier,
    viewModel: StockListViewModel = hiltViewModel()
) {
    val appTitle = stringResource(id = R.string.app_name)
    var isSearchActive by rememberSaveable { mutableStateOf(false) }
    val state by viewModel.homeStockState.collectAsState()
    Scaffold(modifier = modifier,
        topBar = {
            TopBar(
                title = appTitle,
                isSearchActive = isSearchActive,
                onQueryChanged = { TODO() },
                onSearch = { TODO() },
                onActiveChanged = { isSearchActive = it })
        }) { paddingValues ->
        StockList(stockItems = state.stockItems, modifier = modifier.padding(paddingValues))
    }
}

@PreviewLightDark
@Composable
private fun PreviewStockListScreen() {
    HomeStockTrackerTheme {
        StockListScreen()
    }
}