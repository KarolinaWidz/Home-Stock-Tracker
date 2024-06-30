package edu.karolinawidz.homestocktracker.presentation.ui.addnewitem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import edu.karolinawidz.homestocktracker.R
import edu.karolinawidz.homestocktracker.presentation.ui.common.Category
import edu.karolinawidz.homestocktracker.presentation.ui.theme.CornerRadiusMedium
import edu.karolinawidz.homestocktracker.presentation.ui.theme.HomeStockTrackerTheme
import edu.karolinawidz.homestocktracker.presentation.ui.theme.PaddingLarge
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun AddNewItem(
    categoryStateList: ImmutableList<CategoryState>,
    modifier: Modifier = Modifier,
    onCategorySelected: (Category) -> Unit = {},
    onAddItemClicked: () -> Unit = {},
) {
    var name by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = modifier
            .padding(PaddingLarge)
            .fillMaxSize()
    ) {
        NameRegion(name = name, onNameChanged = { name = it })
        Spacer(modifier = modifier.padding(vertical = PaddingLarge))
        CategoryRegion(
            categoryStateList = categoryStateList,
            onCategorySelected = onCategorySelected
        )
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
            text = "Category", style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        CategoryGroup(
            modifier = Modifier.fillMaxWidth(),
            categories = categoryStateList,
            onItemClicked = onCategorySelected,
        )
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