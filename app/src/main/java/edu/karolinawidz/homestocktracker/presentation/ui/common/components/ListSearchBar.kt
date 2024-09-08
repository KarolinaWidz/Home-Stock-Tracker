package edu.karolinawidz.homestocktracker.presentation.ui.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import edu.karolinawidz.homestocktracker.R
import edu.karolinawidz.homestocktracker.presentation.ui.common.StockItem
import edu.karolinawidz.homestocktracker.presentation.ui.stocklist.components.SearchedItemCard
import edu.karolinawidz.homestocktracker.presentation.ui.theme.HomeStockTrackerTheme
import edu.karolinawidz.homestocktracker.presentation.ui.theme.PaddingLarge
import edu.karolinawidz.homestocktracker.presentation.ui.theme.PaddingSmall
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ListSearchBar(
    isSearchActive: Boolean,
    modifier: Modifier = Modifier,
    onQueryChanged: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveChanged: (Boolean) -> Unit,
    searchResult: ImmutableList<StockItem> = persistentListOf(),
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val onQueryChange: (String) -> Unit = { query ->
        searchQuery = query
        onQueryChanged(query)
    }
    SearchBar(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = PaddingSmall,
                bottom = PaddingSmall,
                start = PaddingLarge,
                end = PaddingLarge
            ),
        query = searchQuery,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = isSearchActive,
        onActiveChange = onActiveChanged,
        placeholder = { Text(text = stringResource(R.string.search)) },
        trailingIcon = { SearchIcon() },
        content = {
            LazyColumn {
                items(searchResult) { item ->
                    SearchedItemCard(stockItem = item)
                }
            }
        },
    )
}

@Composable
private fun SearchIcon(modifier: Modifier = Modifier) {
    Icon(modifier = modifier, imageVector = Icons.Filled.Search, contentDescription = null)
}

@PreviewLightDark
@Composable
private fun PreviewTopBar() {
    HomeStockTrackerTheme {
        ListSearchBar(
            isSearchActive = false,
            onQueryChanged = {},
            onSearch = {},
            onActiveChanged = {}
        )
    }
}