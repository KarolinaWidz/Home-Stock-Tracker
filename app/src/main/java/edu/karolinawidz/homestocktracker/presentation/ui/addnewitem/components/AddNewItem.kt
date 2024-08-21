package edu.karolinawidz.homestocktracker.presentation.ui.addnewitem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import edu.karolinawidz.homestocktracker.R
import edu.karolinawidz.homestocktracker.presentation.ui.addnewitem.components.AddNewItemConstants.NAME_FIELD_MAX_LINES
import edu.karolinawidz.homestocktracker.presentation.ui.common.Category
import edu.karolinawidz.homestocktracker.presentation.ui.theme.CornerRadiusMedium
import edu.karolinawidz.homestocktracker.presentation.ui.theme.HomeStockTrackerTheme
import edu.karolinawidz.homestocktracker.presentation.ui.theme.PaddingLarge
import edu.karolinawidz.homestocktracker.presentation.ui.theme.PaddingMedium
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun AddNewItem(
    modifier: Modifier = Modifier,
    name: String = "",
    onNameChanged: (String) -> Unit = {},
    categoryStateList: ImmutableList<CategoryState>,
    onCategorySelected: (Category) -> Unit = {},
    quantity: String = "",
    onQuantityChanged: (String) -> Unit = {},
    onAddItemClicked: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .padding(PaddingLarge)
            .fillMaxSize()
    ) {
        NameRegion(
            name = name,
            onNameChanged = onNameChanged
        )
        Spacer(modifier = modifier.padding(vertical = PaddingMedium))
        CategoryRegion(
            categoryStateList = categoryStateList,
            onCategorySelected = onCategorySelected
        )
        Spacer(modifier = modifier.padding(vertical = PaddingMedium))
        QuantityRegion(
            quantity = quantity,
            onQuantityChanged = onQuantityChanged
        )
        Spacer(modifier = modifier.padding(vertical = PaddingMedium))
        AddButtonRegion(onAddItemClicked = onAddItemClicked)
    }
}

@Composable
private fun NameRegion(
    name: String,
    modifier: Modifier = Modifier,
    onNameChanged: (String) -> Unit = {}
) {
    Column(modifier = modifier) {
        Text(
            modifier = modifier.padding(bottom = PaddingLarge),
            text = stringResource(R.string.name), style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        TextField(
            modifier = modifier.fillMaxWidth(),
            maxLines = NAME_FIELD_MAX_LINES,
            shape = RoundedCornerShape(CornerRadiusMedium),
            placeholder = { Text(text = stringResource(R.string.e_g_tomatoes)) },
            value = name,
            onValueChange = onNameChanged
        )
    }
}

@Composable
private fun CategoryRegion(
    categoryStateList: ImmutableList<CategoryState>,
    modifier: Modifier = Modifier,
    onCategorySelected: (Category) -> Unit = {}
) {
    Column(modifier = modifier) {
        Text(
            modifier = modifier.padding(bottom = PaddingLarge),
            text = stringResource(R.string.category),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        CategoryGroup(
            modifier = Modifier.fillMaxWidth(),
            categories = categoryStateList,
            onItemClicked = onCategorySelected,
        )
    }
}

@Composable
private fun QuantityRegion(
    quantity: String,
    modifier: Modifier = Modifier,
    onQuantityChanged: (String) -> Unit = {}
) {
    Column(modifier = modifier) {
        Text(
            modifier = modifier.padding(bottom = PaddingLarge),
            text = stringResource(R.string.quantity),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        QuantityPanel(
            quantity = quantity,
            onQuantityChanged = onQuantityChanged
        )
    }
}

@Composable
private fun QuantityPanel(
    quantity: String,
    modifier: Modifier = Modifier,
    onQuantityChanged: (String) -> Unit = {}
) {
    Row(modifier = modifier) {
        TextField(
            shape = RoundedCornerShape(CornerRadiusMedium),
            placeholder = { Text(text = stringResource(id = R.string.e_g_one)) },
            value = quantity,
            singleLine = true,
            onValueChange = onQuantityChanged,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
private fun AddButtonRegion(
    modifier: Modifier = Modifier,
    onAddItemClicked: () -> Unit
) {
    Button(modifier = modifier, onClick = onAddItemClicked) {
        Text(text = "Add item")
    }
}

@PreviewLightDark
@Preview(showBackground = true)
@Composable
private fun PreviewAddNewItem() {
    HomeStockTrackerTheme {
        val list = Category.entries.map { CategoryState(category = it) }.toImmutableList()
        AddNewItem(categoryStateList = list)
    }
}

private object AddNewItemConstants {
    const val NAME_FIELD_MAX_LINES = 3
}