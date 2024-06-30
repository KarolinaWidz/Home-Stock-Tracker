package edu.karolinawidz.homestocktracker.presentation.ui.components.common.stockitem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import edu.karolinawidz.homestocktracker.R
import edu.karolinawidz.homestocktracker.presentation.ui.theme.HomeStockTrackerTheme
import edu.karolinawidz.homestocktracker.presentation.ui.theme.PaddingMedium
import edu.karolinawidz.homestocktracker.presentation.ui.theme.PaddingSmall


@Composable
fun StockItemCard(
    stockItem: StockItem,
    modifier: Modifier = Modifier,
    onIncreaseItemClicked: () -> Unit = {},
    onDecreaseItemClicked: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(PaddingSmall),
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = PaddingSmall, horizontal = PaddingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CategoryImage(category = stockItem.category, modifier = Modifier.weight(1f))

            ItemName(name = stockItem.name, modifier = Modifier.weight(5f))

            Text(text = stockItem.quantity.toString(), modifier = Modifier.weight(1f))

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
}

@Composable
private fun CategoryImage(category: Category, modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .padding(PaddingMedium),
        painter = painterResource(id = category.drawable),
        contentDescription = category.toString(),
    )
}

@Composable
private fun ItemName(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier
            .padding(horizontal = PaddingMedium)
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
            contentDescription = stringResource(
                R.string.decrease_number_of_units
            )
        )
    }
}

@PreviewLightDark
@PreviewFontScale
@Composable
private fun PreviewStockListScreen() {
    HomeStockTrackerTheme {
        val item = StockItem("Soap", 1, Category.COSMETICS)
        StockItemCard(item)
    }
}

