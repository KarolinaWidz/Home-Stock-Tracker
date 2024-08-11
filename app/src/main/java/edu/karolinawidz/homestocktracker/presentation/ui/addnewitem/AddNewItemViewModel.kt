package edu.karolinawidz.homestocktracker.presentation.ui.addnewitem

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.karolinawidz.homestocktracker.data.repository.StockItemRepository
import edu.karolinawidz.homestocktracker.presentation.ui.addnewitem.components.CategoryState
import edu.karolinawidz.homestocktracker.presentation.ui.common.Category
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddNewItemViewModel @Inject constructor(
    private val repository: StockItemRepository
) : ViewModel() {

    private var _newItemScreenState = MutableStateFlow(AddNewItemState())
    val newItemScreenState: StateFlow<AddNewItemState> = _newItemScreenState

    private var _categoryItems = MutableStateFlow(
        Category.entries.map { category -> CategoryState(category = category) }.toImmutableList()
    )
    val categoryItems: StateFlow<ImmutableList<CategoryState>> = _categoryItems

    fun categorySelected(category: Category) {
        _categoryItems.update { items -> items.map { it.copy(isSelected = it.category == category) }.toImmutableList() }
    }
}