package edu.karolinawidz.homestocktracker.presentation.ui.addnewitem.components

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import edu.karolinawidz.homestocktracker.presentation.ui.common.Category
import edu.karolinawidz.homestocktracker.presentation.ui.theme.HomeStockTrackerTheme
import edu.karolinawidz.homestocktracker.presentation.ui.theme.PaddingExtraSmall
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryGroup(
    categories: ImmutableList<CategoryState>,
    modifier: Modifier = Modifier,
    onItemClicked: (Category) -> Unit = {}
) {
    FlowRow(modifier = modifier) {
        for (state in categories) {
            CategoryItem(
                item = state.category.name,
                selected = state.isSelected,
                onCategoryClicked = { onItemClicked(state.category) },
            )
        }
    }
}

@Composable
private fun CategoryItem(
    item: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onCategoryClicked: () -> Unit = {}
) {
    InputChip(
        modifier = modifier.padding(horizontal = PaddingExtraSmall),
        selected = selected,
        onClick = onCategoryClicked,
        label = { Text(text = item) })
}

@PreviewLightDark
@Preview(showBackground = true)
@Composable
private fun PreviewAddNewItem() {
    HomeStockTrackerTheme {
        val list = Category.entries.map { CategoryState(category = it) }.toImmutableList()
        CategoryGroup(list)
    }
}