package edu.karolinawidz.homestocktracker.presentation.ui.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import edu.karolinawidz.homestocktracker.R
import edu.karolinawidz.homestocktracker.presentation.ui.addnewitem.AddNewItemViewModel
import edu.karolinawidz.homestocktracker.presentation.ui.addnewitem.components.AddNewItem
import edu.karolinawidz.homestocktracker.presentation.ui.common.components.TopTitleBarWithNavigation
import edu.karolinawidz.homestocktracker.presentation.ui.theme.SpacerMedium
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AddNewItemScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit = {},
    viewModel: AddNewItemViewModel = hiltViewModel()
) {
    val screenTitle = stringResource(id = R.string.add_new_item)

    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(state.savingState.isSavingError) {
        if (state.savingState.isSavingError) {
            snackbarHostState.showSnackbar(
                context.getString(R.string.cannot_add_item),
                duration = SnackbarDuration.Short,
                actionLabel = context.getString(R.string.ok)
            )
        }
    }

    LaunchedEffect(state.savingState.isSaved) {
        if (state.savingState.isSaved) {
            Toast.makeText(context, R.string.item_saved_successfully, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
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
            else -> {
                AddNewItem(
                    modifier = modifier.padding(paddingValues),
                    name = state.newItem.name ?: "",
                    isNameError = state.addNewItemError.isNameError,
                    onNameChanged = { name -> viewModel.nameUpdated(name) },
                    categories = viewModel.provideCategories(),
                    selectedCategory = state.newItem.category,
                    onCategorySelected = { category -> viewModel.categorySelected(category = category) },
                    quantity = state.newItem.quantity ?: 0,
                    onQuantityChanged = { quantity -> viewModel.quantityChanged(quantity) },
                    onAddItemClicked = {
                        viewModel.addItem()
                        coroutineScope.launch {
                            if (state.savingState.isSavingError) {
                                snackbarHostState.showSnackbar(
                                    context.getString(R.string.cannot_add_item),
                                    duration = SnackbarDuration.Short,
                                    actionLabel = context.getString(R.string.ok)
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}