package edu.karolinawidz.homestocktracker.presentation.ui.screen

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import edu.karolinawidz.homestocktracker.presentation.ui.components.addnewitem.AddNewItemViewModel

@Composable
fun AddNewItemScreen(
    modifier: Modifier = Modifier,
    onAddItemClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {},
    viewModel: AddNewItemViewModel = hiltViewModel()
) {
    val state by viewModel.newItemScreenState.collectAsState()

    when {
        state.isLoading -> CircularProgressIndicator(modifier = modifier)
        state.isError -> {
            TODO()
        }

        else -> AddNewItem(modifier = modifier, onAddItemClicked = onAddItemClicked)
    }
}

@Composable
fun AddNewItem(modifier: Modifier = Modifier, onAddItemClicked: () -> Unit = {}) {

}