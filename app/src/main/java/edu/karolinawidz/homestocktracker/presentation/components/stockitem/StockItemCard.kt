package edu.karolinawidz.homestocktracker.presentation.components.stockitem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import edu.karolinawidz.homestocktracker.R
import edu.karolinawidz.homestocktracker.presentation.theme.HomeStockTrackerTheme
import edu.karolinawidz.homestocktracker.presentation.theme.PaddingMedium
import edu.karolinawidz.homestocktracker.presentation.theme.PaddingSmall


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
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = PaddingSmall, horizontal = PaddingMedium),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stockItem.name, modifier = Modifier.weight(5f))
            Text(text = stockItem.quantity.toString(), modifier = Modifier.weight(1f))
            IncreaseIconButton(onIncreaseItemClicked = onIncreaseItemClicked)
            DecreaseIconButton(onDecreaseItemClicked = onDecreaseItemClicked)
        }
    }
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
@Composable
private fun PreviewStockListScreen() {
    HomeStockTrackerTheme {
        val item = StockItem("Soap", 1, Category.Cosmetics)
        StockItemCard(item)
    }
}

