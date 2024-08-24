package edu.karolinawidz.homestocktracker.presentation.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import edu.karolinawidz.homestocktracker.presentation.ui.common.components.FullScreenLoadingIndicator
import edu.karolinawidz.homestocktracker.presentation.ui.common.components.NoItemsBanner
import edu.karolinawidz.homestocktracker.presentation.ui.common.components.TopBar
import edu.karolinawidz.homestocktracker.presentation.ui.stocklist.StockListViewModel
import edu.karolinawidz.homestocktracker.presentation.ui.stocklist.components.AddItemFab
import edu.karolinawidz.homestocktracker.presentation.ui.stocklist.components.StockList
import edu.karolinawidz.homestocktracker.presentation.ui.theme.HomeStockTrackerTheme

@Composable
fun StockListScreen(
    modifier: Modifier = Modifier,
    onAddItemClicked: () -> Unit = {},
    viewModel: StockListViewModel = hiltViewModel()
) {
    val appTitle = stringResource(id = R.string.app_name)
    var isSearchActive by rememberSaveable { mutableStateOf(false) }
    val state by viewModel.homeStockState.collectAsState()


    LaunchedEffect(key1 = Unit) {
        viewModel.loadHomeStock()
    }

    Scaffold(modifier = modifier,
        topBar = {
            TopBar(
                title = appTitle,
                isSearchActive = isSearchActive,
                onQueryChanged = { TODO() },
                onSearch = { TODO() },
                onActiveChanged = { isSearchActive = it },
                onSortClicked = { viewModel.changeOrder() }
            )
        },
        floatingActionButton = {
            AddItemFab(onClick = onAddItemClicked)
        })

    { paddingValues ->

        when {
            state.isLoading -> FullScreenLoadingIndicator(modifier = modifier.padding(paddingValues))
            state.stockItems.isNotEmpty() -> StockList(
                stockItems = state.stockItems,
                modifier = modifier.padding(paddingValues),
                onIncreaseItemClicked = { item -> viewModel.increaseAmountForItem(item) },
                onDecreaseItemClicked = { item -> viewModel.decreaseAmountForItem(item) }
            )

            else -> NoItemsBanner(modifier = modifier.padding(paddingValues))
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewStockListScreen() {
    HomeStockTrackerTheme {
        StockListScreen()
    }
}