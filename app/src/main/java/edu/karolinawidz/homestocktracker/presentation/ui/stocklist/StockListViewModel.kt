package edu.karolinawidz.homestocktracker.presentation.ui.stocklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.karolinawidz.homestocktracker.data.repository.StockItemRepository
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
}


