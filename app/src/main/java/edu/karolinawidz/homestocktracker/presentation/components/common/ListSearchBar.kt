package edu.karolinawidz.homestocktracker.presentation.components.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import edu.karolinawidz.homestocktracker.R
import edu.karolinawidz.homestocktracker.presentation.theme.HomeStockTrackerTheme
import edu.karolinawidz.homestocktracker.presentation.theme.PaddingSmall

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ListSearchBar(
    isSearchActive: Boolean,
    modifier: Modifier = Modifier,
    onQueryChanged: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveChanged: (Boolean) -> Unit,
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val onQueryChange: (String) -> Unit = { query ->
        searchQuery = query
        onQueryChanged(query)
    }
    SearchBar(
        modifier = modifier
            .fillMaxWidth()
            .padding(PaddingSmall),
        query = searchQuery,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = isSearchActive,
        onActiveChange = onActiveChanged,
        placeholder = { Text(text = stringResource(R.string.search)) },
        trailingIcon = { SearchIcon() },
        content = {},
    )
}

@Composable
private fun SearchIcon(modifier: Modifier = Modifier) {
    Icon(modifier = modifier, imageVector = Icons.Filled.Search, contentDescription = null)
}

@PreviewLightDark
@PreviewDynamicColors
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