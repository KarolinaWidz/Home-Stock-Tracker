package edu.karolinawidz.homestocktracker.presentation.components.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import edu.karolinawidz.homestocktracker.R
import edu.karolinawidz.homestocktracker.presentation.theme.HomeStockTrackerTheme
import edu.karolinawidz.homestocktracker.presentation.theme.PaddingSmall

@Composable
internal fun TopBar(
    title: String, isSearchActive: Boolean,
    onQueryChanged: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        TopTitleBar(title = title)
        ListSearchBar(
            isSearchActive = isSearchActive,
            onQueryChanged = onQueryChanged,
            onSearch = onSearch,
            onActiveChanged = onActiveChanged
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = PaddingSmall))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopTitleBar(title: String, modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier,
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
        title = { TitleText(title = title) },
        actions = { TopTitleBarActions() },
    )
}

@Composable
private fun TitleText(title: String, modifier: Modifier = Modifier) {
    Text(modifier = modifier, text = title)
}

@Composable
private fun TopTitleBarActions(
    modifier: Modifier = Modifier,
    onSortClicked: () -> Unit = {}
) {
    Row {
        IconButton(modifier = modifier, onClick = onSortClicked) {
            Icon(
                painter = painterResource(id = R.drawable.ic_sort),
                contentDescription = stringResource(
                    R.string.sort_stock_items
                )
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewTopBar() {
    HomeStockTrackerTheme {
        TopBar(
            title = "Home Stock Tracker",
            isSearchActive = false,
            onQueryChanged = {},
            onSearch = {},
            onActiveChanged = {},
        )
    }
}