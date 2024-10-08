package edu.karolinawidz.homestocktracker.presentation.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import edu.karolinawidz.homestocktracker.R
import edu.karolinawidz.homestocktracker.presentation.ui.common.components.FullScreenLoadingIndicator
import edu.karolinawidz.homestocktracker.presentation.ui.common.components.NoItemsBanner
import edu.karolinawidz.homestocktracker.presentation.ui.common.components.TopBar
import edu.karolinawidz.homestocktracker.presentation.ui.stocklist.StockListIntent
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
    val state by viewModel.state.collectAsState()


    LaunchedEffect(key1 = Unit) {
        viewModel.processIntent(StockListIntent.LoadStock)
    }

    Scaffold(modifier = modifier,
        topBar = {
            TopBar(
                title = appTitle,
                isSearchActive = state.isSearchActive,
                onQueryChanged = { query ->
                    viewModel.processIntent(
                        StockListIntent.SearchQueryChanged(
                            query = query
                        )
                    )
                },
                onSearch = { query ->
                    viewModel.processIntent(
                        StockListIntent.SearchQueryChanged(
                            query = query
                        )
                    )
                },
                onActiveChanged = { isActive ->
                    viewModel.processIntent(
                        StockListIntent.OnSearchStateChange(
                            isActive = isActive
                        )
                    )
                },
                searchResult = state.searchResult,
                onSortClicked = { viewModel.processIntent(StockListIntent.ChangeOrder) }
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
                onItemDelete = { item -> viewModel.processIntent(StockListIntent.DeleteItem(item = item)) },
                onIncreaseItemClicked = { item ->
                    viewModel.processIntent(
                        StockListIntent.IncreaseAmountOfItem(
                            item = item
                        )
                    )
                },
                onDecreaseItemClicked = { item ->
                    viewModel.processIntent(
                        StockListIntent.DecreaseAmountOfItem(
                            item = item
                        )
                    )
                }
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