package edu.karolinawidz.homestocktracker.presentation.ui.stocklist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import edu.karolinawidz.homestocktracker.R
import edu.karolinawidz.homestocktracker.presentation.ui.common.Category
import edu.karolinawidz.homestocktracker.presentation.ui.common.StockItem
import edu.karolinawidz.homestocktracker.presentation.ui.theme.CornerRadiusSmall
import edu.karolinawidz.homestocktracker.presentation.ui.theme.HomeStockTrackerTheme
import edu.karolinawidz.homestocktracker.presentation.ui.theme.PaddingMedium
import edu.karolinawidz.homestocktracker.presentation.ui.theme.PaddingSmall
import edu.karolinawidz.homestocktracker.presentation.ui.theme.SizeMedium
import edu.karolinawidz.homestocktracker.presentation.ui.theme.Typography


@Composable
fun StockItemCard(
    stockItem: StockItem,
    modifier: Modifier = Modifier,
    onIncreaseItemClicked: () -> Unit = {},
    onDecreaseItemClicked: () -> Unit = {}
) {

    Row(
        modifier
            .fillMaxWidth()
            .padding(vertical = PaddingSmall, horizontal = PaddingSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CategoryImage(category = stockItem.category)

        ItemText(
            modifier = Modifier.weight(4f),
            name = stockItem.name,
            quantity = stockItem.quantity
        )

        IncreaseIconButton(
            modifier = Modifier.weight(1f),
            onIncreaseItemClicked = onIncreaseItemClicked
        )

        DecreaseIconButton(
            modifier = Modifier.weight(1f),
            onDecreaseItemClicked = onDecreaseItemClicked
        )
    }
}

@Composable
private fun CategoryImage(category: Category, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(horizontal = PaddingMedium)
            .size(SizeMedium),
        shape = RoundedCornerShape(CornerRadiusSmall)
    ) {
        Image(
            modifier = modifier.padding(PaddingMedium),
            painter = painterResource(id = category.drawable),
            contentDescription = category.toString(),
        )
    }
}

@Composable
private fun ItemText(name: String, quantity: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(PaddingSmall),
        verticalArrangement = Arrangement.spacedBy(PaddingSmall)
    ) {
        ItemName(name = name)
        ItemQuantity(quantity = quantity)
    }
}

@Composable
private fun ItemName(name: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = name,
        style = Typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground,
    )
}

@Composable
fun ItemQuantity(quantity: Int, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.quantity_list, quantity),
        style = Typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun IncreaseIconButton(
    onIncreaseItemClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(modifier = modifier, onClick = onIncreaseItemClicked) {
        Icon(
            painter = painterResource(id = R.drawable.ic_up),
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = stringResource(
                R.string.increase_number_of_units
            )
        )
    }
}

@Composable
private fun DecreaseIconButton(
    onDecreaseItemClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier,
        onClick = onDecreaseItemClicked
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_down),
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = stringResource(
                R.string.decrease_number_of_units
            )
        )
    }
}

@PreviewLightDark
@PreviewFontScale
@Preview(showBackground = true)
@Composable
private fun PreviewStockListScreen() {
    HomeStockTrackerTheme {
        val item = StockItem("Soap", 1, Category.COSMETICS)
        StockItemCard(item)
    }
}

