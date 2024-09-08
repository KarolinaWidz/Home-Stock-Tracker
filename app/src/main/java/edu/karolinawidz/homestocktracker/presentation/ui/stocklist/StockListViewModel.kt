package edu.karolinawidz.homestocktracker.presentation.ui.stocklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.karolinawidz.homestocktracker.data.local.Item
import edu.karolinawidz.homestocktracker.data.repository.StockItemRepository
import edu.karolinawidz.homestocktracker.presentation.ui.common.StockItem
import edu.karolinawidz.homestocktracker.presentation.ui.common.toStockItems
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockListViewModel @Inject constructor(
    private val repository: StockItemRepository
) : ViewModel() {

    private var _state = MutableStateFlow(StockState())
    val state: StateFlow<StockState> = _state

    fun processIntent(intent: StockListIntent) {
        when (intent) {
            is StockListIntent.DecreaseAmountOfItem -> decreaseAmountForItem(item = intent.item)
            is StockListIntent.IncreaseAmountOfItem -> increaseAmountForItem(item = intent.item)
            is StockListIntent.DeleteItem -> deleteItem(item = intent.item)
            StockListIntent.LoadStock -> loadHomeStock()
            StockListIntent.ChangeOrder -> changeOrder()
            is StockListIntent.SearchQueryChanged -> onSearchQueryChanged(query = intent.query)
            is StockListIntent.OnSearchStateChange -> onSearchActiveChanged(isSearchActive = intent.isActive)
        }
    }

    private fun decreaseAmountForItem(item: StockItem) {
        val updatedQuantity = if (item.quantity > 0) item.quantity - 1 else item.quantity
        val updatedItem = Item(
            name = item.name,
            quantity = updatedQuantity,
            category = item.category.name
        )
        viewModelScope.launch {
            repository.updateItem(updatedItem)
        }
    }

    private fun increaseAmountForItem(item: StockItem) {
        val updatedItem = Item(
            name = item.name,
            quantity = item.quantity + 1,
            category = item.category.name
        )
        viewModelScope.launch {
            repository.updateItem(updatedItem)
        }
    }

    private fun loadHomeStock() {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(isLoading = true)
            }

            repository.getAllItems().collectLatest {
                _state.update { state ->
                    state.copy(
                        isLoading = false,
                        stockItems = it.getSortedItems(_state.value.isOrderAscending)
                            .toStockItems()
                    )
                }
            }
        }
    }

    private fun changeOrder() {
        val currentOrder = _state.value.isOrderAscending
        _state.update { state -> state.copy(isOrderAscending = !currentOrder) }
        loadHomeStock()
    }

    private fun deleteItem(item: StockItem) {
        val itemToDelete = Item(
            name = item.name,
            quantity = item.quantity,
            category = item.category.name
        )
        viewModelScope.launch {
            repository.deleteItem(itemToDelete)
        }
    }

    private fun onSearchQueryChanged(query: String) {
        _state.update { state -> state.copy(searchQuery = query) }
        if (query.isNotBlank()) {
            viewModelScope.launch {
                repository.findItemsByName(query).collectLatest {
                    _state.update { state ->
                        state.copy(
                            searchResult = it.toStockItems()
                        )
                    }
                }
            }
        } else {
            _state.update { state ->
                state.copy(
                    searchResult = persistentListOf()
                )
            }
        }
    }

    private fun onSearchActiveChanged(isSearchActive: Boolean) {
        _state.update { state -> state.copy(isSearchActive = isSearchActive) }
    }

    private fun List<Item>.getSortedItems(isAsc: Boolean) = if (!isAsc) this.reversed() else this
}


