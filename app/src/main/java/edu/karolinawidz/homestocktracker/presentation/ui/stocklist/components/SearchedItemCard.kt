package edu.karolinawidz.homestocktracker.presentation.ui.stocklist.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import edu.karolinawidz.homestocktracker.R
import edu.karolinawidz.homestocktracker.presentation.ui.common.Category
import edu.karolinawidz.homestocktracker.presentation.ui.common.StockItem
import edu.karolinawidz.homestocktracker.presentation.ui.common.components.CategoryImage
import edu.karolinawidz.homestocktracker.presentation.ui.theme.HomeStockTrackerTheme
import edu.karolinawidz.homestocktracker.presentation.ui.theme.PaddingMedium
import edu.karolinawidz.homestocktracker.presentation.ui.theme.PaddingSmall
import edu.karolinawidz.homestocktracker.presentation.ui.theme.SizeSmall
import edu.karolinawidz.homestocktracker.presentation.ui.theme.Typography


@Composable
fun SearchedItemCard(
    stockItem: StockItem,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(vertical = PaddingSmall, horizontal = PaddingSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CategoryImage(
            category = stockItem.category,
            size = SizeSmall,
            backgroundColor = MaterialTheme.colorScheme.background
        )

        SearchedItemText(
            modifier = Modifier.weight(4f),
            name = stockItem.name,
            quantity = stockItem.quantity
        )
    }
}

@Composable
private fun SearchedItemText(name: String, quantity: Long, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(PaddingSmall),
    ) {
        SearchedItemName(name = name)
        Spacer(modifier = Modifier.weight(1f))
        SearchedItemQuantity(quantity = quantity)
    }
}

@Composable
private fun SearchedItemName(name: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = name,
        style = Typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground,
    )
}

@Composable
fun SearchedItemQuantity(quantity: Long, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.padding(horizontal = PaddingMedium),
        text = stringResource(R.string.quantity_list, quantity),
        style = Typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@PreviewLightDark
@PreviewFontScale
@Preview(showBackground = true)
@Composable
private fun PreviewStockListScreen() {
    HomeStockTrackerTheme {
        val item = StockItem("Soap", 1, Category.COSMETICS)
        SearchedItemCard(item)
    }
}

