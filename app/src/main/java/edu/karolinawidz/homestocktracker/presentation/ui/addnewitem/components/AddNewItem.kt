package edu.karolinawidz.homestocktracker.presentation.ui.addnewitem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import edu.karolinawidz.homestocktracker.presentation.ui.theme.PaddingExtraLarge
import edu.karolinawidz.homestocktracker.presentation.ui.theme.PaddingLarge
import edu.karolinawidz.homestocktracker.presentation.ui.theme.PaddingSmall
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun AddNewItem(
    modifier: Modifier = Modifier,
    name: String = "",
    onNameChanged: (String) -> Unit = {},
    isNameError: Boolean = false,
    categories: ImmutableList<Category>,
    selectedCategory: Category? = null,
    onCategorySelected: (Category) -> Unit = {},
    quantity: String = "",
    onQuantityChanged: (String) -> Unit = {},
    isQuantityError: Boolean = false,
    onAddItemClicked: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .padding(PaddingLarge)
            .fillMaxSize()
    ) {
        ItemNameField(
            name = name,
            isError = isNameError,
            onNameChanged = onNameChanged
        )
        QuantityField(
            quantity = quantity,
            isError = isQuantityError,
            onQuantityChanged = onQuantityChanged
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = PaddingLarge))
        CategoryRegion(
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = onCategorySelected
        )
        Spacer(
            modifier = Modifier
                .padding(vertical = PaddingExtraLarge)
                .weight(1f)
        )
        AddButtonRegion(onAddItemClicked = onAddItemClicked)
    }
}

@Composable
private fun ItemNameField(
    name: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    onNameChanged: (String) -> Unit = {}
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = PaddingSmall),
        value = name,
        onValueChange = onNameChanged,
        maxLines = NAME_FIELD_MAX_LINES,
        label = { Text(text = stringResource(R.string.name)) },
        shape = RoundedCornerShape(CornerRadiusMedium),
        isError = isError,
        trailingIcon = {
            if (isError) {
                Icon(
                    imageVector = Icons.Default.Info,
                    tint = MaterialTheme.colorScheme.error,
                    contentDescription = null
                )
            }
        },
        supportingText = {
            if (isError)
                Text(
                    text = stringResource(id = R.string.the_name_field_is_required_please_provide_a_name),
                    color = MaterialTheme.colorScheme.error
                )
        },
    )
}

@Composable
private fun QuantityField(
    quantity: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    onQuantityChanged: (String) -> Unit = {}
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = PaddingSmall),
        shape = RoundedCornerShape(CornerRadiusMedium),
        value = quantity,
        label = { Text(text = stringResource(R.string.quantity)) },
        singleLine = true,
        onValueChange = onQuantityChanged,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        isError = isError,
        trailingIcon = {
            if (isError) {
                Icon(
                    imageVector = Icons.Default.Info,
                    tint = MaterialTheme.colorScheme.error,
                    contentDescription = null
                )
            }
        },
        supportingText = {
            if (isError)
                Text(
                    text = stringResource(R.string.the_quantity_field_is_required_please_provide_a_valid_quantity),
                    color = MaterialTheme.colorScheme.error
                )
        },
    )
}


@Composable
private fun CategoryRegion(
    categories: ImmutableList<Category>,
    modifier: Modifier = Modifier,
    onCategorySelected: (Category) -> Unit = {},
    selectedCategory: Category?
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .padding(bottom = PaddingLarge)
                .align(Alignment.CenterHorizontally),
            text = stringResource(R.string.category),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        CategoryGroup(
            modifier = Modifier.fillMaxWidth(),
            categories = categories,
            selectedCategory = selectedCategory,
            onItemClicked = onCategorySelected,
        )
    }
}

@Composable
private fun AddButtonRegion(
    modifier: Modifier = Modifier,
    onAddItemClicked: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onAddItemClicked,
    ) {
        Text(text = stringResource(R.string.add_item))
    }
}

@PreviewLightDark
@Preview(showBackground = true)
@Composable
private fun PreviewAddNewItem() {
    HomeStockTrackerTheme {
        val list = Category.entries.toImmutableList()
        AddNewItem(categories = list)
    }
}

private object AddNewItemConstants {
    const val NAME_FIELD_MAX_LINES = 3
}