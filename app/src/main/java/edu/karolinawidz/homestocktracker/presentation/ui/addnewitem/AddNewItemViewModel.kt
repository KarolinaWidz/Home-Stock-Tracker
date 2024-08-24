package edu.karolinawidz.homestocktracker.presentation.ui.addnewitem

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.karolinawidz.homestocktracker.data.local.Item
import edu.karolinawidz.homestocktracker.data.repository.StockItemRepository
import edu.karolinawidz.homestocktracker.presentation.ui.common.Category
import edu.karolinawidz.homestocktracker.presentation.ui.common.StockItem
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

    fun provideCategories() = Category.getEntries().toImmutableList()

    fun categorySelected(category: Category) {
        val currentItem = _state.value.newItem ?: StockItem()
        _state.update { state -> state.copy(newItem = currentItem.copy(category = category)) }
        resetAddItemState()
    }

    fun nameUpdated(name: String) {
        val currentItem = _state.value.newItem ?: StockItem()
        _state.update { state -> state.copy(newItem = currentItem.copy(name = name)) }
        resetAddItemState()
    }

    fun quantityChanged(quantity: String) {
        val currentItem = _state.value.newItem ?: StockItem()
        val validatedQuantity = if (quantity.isNotBlank() && quantity.isDigitsOnly()) {
            quantity.toInt()
        } else {
            0
        }
        _state.update { state -> state.copy(newItem = currentItem.copy(quantity = validatedQuantity)) }
        resetAddItemState()
    }

    fun addItem() {
        val item = _state.value.newItem
        if (item != null && item.name.isNotBlank()) {
            val itemToAdd = Item(
                name = item.name,
                quantity = item.quantity,
                category = item.category.name
            )
            _state.update { state -> state.copy(isLoading = true) }

            viewModelScope.launch {
                repository.addItem(itemToAdd)
            }

            _state.update { state -> state.copy(isLoading = false, isSaved = true) }

        } else {
            _state.update { state -> state.copy(isSaved = false, isError = true) }
        }
    }

    fun resetAddItemState() {
        _state.update { state ->
            state.copy(
                isSaved = false,
                isError = false,
            )
        }
    }
}
