package edu.karolinawidz.homestocktracker.presentation.ui.stocklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.karolinawidz.homestocktracker.data.local.Item
import edu.karolinawidz.homestocktracker.data.repository.StockItemRepository
import edu.karolinawidz.homestocktracker.presentation.ui.common.StockItem
import edu.karolinawidz.homestocktracker.presentation.ui.common.toStockItems
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

    private var _homeStockState = MutableStateFlow(HomeStockState())
    val homeStockState: StateFlow<HomeStockState> = _homeStockState

    fun loadHomeStock() {
        viewModelScope.launch {
            _homeStockState.update { state ->
                state.copy(isLoading = true)
            }

            repository.getAllItems().collectLatest {
                _homeStockState.update { state ->
                    state.copy(
                        isLoading = false,
                        stockItems = it.toStockItems()
                    )
                }
            }
        }
    }

    fun increaseAmountForItem(item: StockItem) {
        val updatedItem = Item(
            name = item.name,
            quantity = item.quantity + 1,
            category = item.category.name
        )
        viewModelScope.launch {
            repository.updateItem(updatedItem)
        }
    }

    fun decreaseAmountForItem(item: StockItem) {
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
}


