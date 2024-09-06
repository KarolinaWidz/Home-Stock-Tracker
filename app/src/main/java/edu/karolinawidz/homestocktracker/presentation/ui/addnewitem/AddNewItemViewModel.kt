package edu.karolinawidz.homestocktracker.presentation.ui.addnewitem

import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.karolinawidz.homestocktracker.data.local.Item
import edu.karolinawidz.homestocktracker.data.repository.StockItemRepository
import edu.karolinawidz.homestocktracker.presentation.ui.common.Category
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewItemViewModel @Inject constructor(
    private val repository: StockItemRepository
) : ViewModel() {

    private var _state = MutableStateFlow(AddNewItemState())
    val state: StateFlow<AddNewItemState> = _state

    fun processIntent(intent: AddNewItemIntent) {
        when (intent) {
            AddNewItemIntent.AddItem -> addItem()
            is AddNewItemIntent.UpdateCategory -> categoryUpdated(intent.category)
            is AddNewItemIntent.UpdateName -> nameUpdated(intent.name)
            is AddNewItemIntent.UpdateQuantity -> quantityUpdated(intent.quantity)
            AddNewItemIntent.CleanItem -> resetItemState()
            AddNewItemIntent.DismissError -> resetSavingError()
        }
    }

    fun provideCategories() = Category.getEntries().toImmutableList()

    private fun categoryUpdated(category: Category) {
        val currentItem = _state.value.newItem
        _state.update { state -> state.copy(newItem = currentItem.copy(category = category)) }
    }

    private fun nameUpdated(name: String) {
        if (name.isNotBlank()) {
            _state.update { state ->
                state.copy(
                    addNewItemError = state.addNewItemError.copy(
                        isNameError = false
                    ),
                    newItem = state.newItem.copy(name = name)
                )
            }
        } else {
            _state.update { state ->
                state.copy(
                    addNewItemError = state.addNewItemError.copy(
                        isNameError = true
                    ),
                    newItem = state.newItem.copy(name = name)
                )
            }
        }
    }

    private fun quantityUpdated(quantity: String) {
        val currentItem = _state.value.newItem
        val validatedQuantity = if (quantity.isNotBlank() && quantity.isDigitsOnly()) {
            quantity.toLong()
        } else {
            0
        }
        _state.update { state -> state.copy(newItem = currentItem.copy(quantity = validatedQuantity)) }
    }

    private fun addItem() {
        val item = _state.value.newItem
        val error = _state.value.addNewItemError
        if (canItemBeAdded(item = item, error = error)) {
            _state.update { state -> state.copy(isLoading = true) }

            val itemToAdd = Item(
                name = item.name!!,
                quantity = 0,
                category = item.category.name
            )

            viewModelScope.launch {
                repository.addItem(itemToAdd)
            }

            _state.update { state ->
                state.copy(
                    isLoading = false,
                    savingState = state.savingState.copy(isSaved = true, isSavingError = false)
                )
            }
            Log.i(TAG, "State updated to ${state.value}")
        } else {
            _state.update { state ->
                state.copy(
                    savingState = state.savingState.copy(
                        isSaved = false,
                        isSavingError = true
                    )
                )
            }
            Log.i(TAG, "State updated to ${state.value}")
        }
    }

    private fun canItemBeAdded(item: NewItem, error: AddNewItemError) =
        item.name != null && !error.isNameError

    private fun resetItemState() {
        _state.update { AddNewItemState() }
        Log.i(TAG, "State updated to ${state.value}")
    }

    private fun resetSavingError() {
        _state.update { state ->
            state.copy(
                savingState = state.savingState.copy(
                    isSavingError = false
                )
            )
        }
    }

    private companion object {
        const val TAG = "AddNewItemViewModel"
    }
}
