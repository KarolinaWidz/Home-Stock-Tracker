package edu.karolinawidz.homestocktracker.presentation.ui.addnewitem

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.karolinawidz.homestocktracker.data.repository.StockItemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AddNewItemViewModel @Inject constructor(
    private val repository: StockItemRepository
) : ViewModel() {

    private var _newItemScreenState = MutableStateFlow(AddNewItemState())
    val newItemScreenState: StateFlow<AddNewItemState> = _newItemScreenState
}