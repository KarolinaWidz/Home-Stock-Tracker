package edu.karolinawidz.homestocktracker.presentation.ui.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import edu.karolinawidz.homestocktracker.R
import edu.karolinawidz.homestocktracker.presentation.ui.theme.HomeStockTrackerTheme
import edu.karolinawidz.homestocktracker.presentation.ui.theme.SpacerMedium

@Composable
internal fun TopBar(
    title: String, isSearchActive: Boolean,
    onQueryChanged: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    onSortClicked: () -> Unit = {}
) {
    Column(modifier = modifier) {
        TopTitleBar(
            navigationIcon = { Spacer(modifier = Modifier.size(SpacerMedium)) },
            title = title
        ) {
            TopTitleBarActions(onSortClicked = onSortClicked)
        }
        ListSearchBar(
            isSearchActive = isSearchActive,
            onQueryChanged = onQueryChanged,
            onSearch = onSearch,
            onActiveChanged = onActiveChanged
        )
    }
}

@Composable
fun TopTitleBarWithNavigation(
    title: String,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit = {},
    actions: @Composable (RowScope.() -> Unit) = { }
) {
    TopTitleBar(
        modifier = modifier,
        navigationIcon = { NavigationIcon(onBackClicked = onBackClicked) },
        title = title,
        actions = actions,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopTitleBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable (RowScope.() -> Unit) = { }
) {
    TopAppBar(
        modifier = modifier,
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
        navigationIcon = navigationIcon,
        title = { TitleText(title = title) },
        actions = actions,
    )
}

@Composable
private fun TitleText(title: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = title,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface,
    )
}

@Composable
private fun NavigationIcon(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit = {}
) {
    IconButton(modifier = modifier, onClick = onBackClicked) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = stringResource(
                R.string.navigate_back
            ),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun TopTitleBarActions(
    modifier: Modifier = Modifier,
    onSortClicked: () -> Unit = {}
) {
    var isAsc by rememberSaveable { mutableStateOf(false) }
    Row {
        IconButton(modifier = modifier,
            onClick = {
                onSortClicked()
                isAsc = !isAsc
            }) {
            Icon(
                painter = sortIcon(isAsc = isAsc),
                contentDescription = stringResource(
                    R.string.sort_stock_items
                ),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun sortIcon(isAsc: Boolean) =
    if (isAsc) painterResource(id = R.drawable.ic_sort_asc) else painterResource(
        id = R.drawable.ic_sort_desc
    )

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

@PreviewLightDark
@Composable
private fun PreviewTopBarWithNavigation() {
    HomeStockTrackerTheme {
        TopTitleBarWithNavigation(
            title = "Home Stock Tracker",
            actions = { Spacer(modifier = Modifier.size(SpacerMedium)) }
        )
    }
}