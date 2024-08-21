package edu.karolinawidz.homestocktracker.presentation.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import edu.karolinawidz.homestocktracker.R
import edu.karolinawidz.homestocktracker.presentation.ui.addnewitem.AddNewItemViewModel
import edu.karolinawidz.homestocktracker.presentation.ui.addnewitem.components.AddNewItem
import edu.karolinawidz.homestocktracker.presentation.ui.common.components.TopTitleBarWithNavigation
import edu.karolinawidz.homestocktracker.presentation.ui.theme.SpacerMedium

@Composable
fun AddNewItemScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit = {},
    viewModel: AddNewItemViewModel = hiltViewModel()
) {
    val screenTitle = stringResource(id = R.string.add_new_item)

    val state by viewModel.newItemScreenState.collectAsState()

    Scaffold(modifier = modifier,
        topBar = {
            TopTitleBarWithNavigation(
                title = screenTitle,
                onBackClicked = onBackClicked,
                actions = { Spacer(modifier = Modifier.size(SpacerMedium)) })
        }
    )

    { paddingValues ->
        when {
            state.isLoading -> CircularProgressIndicator(modifier = modifier.padding(paddingValues))
            state.isError -> {
                TODO()
            }

            else -> {
                AddNewItem(
                    modifier = modifier.padding(paddingValues),
                    name = state.newItem?.name ?: "",
                    onNameChanged = { name -> viewModel.nameUpdated(name) },
                    categories = viewModel.provideCategories(),
                    selectedCategory= state.newItem?.category,
                    onCategorySelected = { category -> viewModel.categorySelected(category = category) },
                    quantity = state.newItem?.quantity ?: 0,
                    onQuantityChanged = { quantity -> viewModel.quantityChanged(quantity) },
                    onAddItemClicked = { viewModel.addItem() }
                )
            }
        }
    }
}