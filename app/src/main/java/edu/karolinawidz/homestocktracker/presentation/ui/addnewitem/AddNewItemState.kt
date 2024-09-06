package edu.karolinawidz.homestocktracker.presentation.ui.addnewitem

import edu.karolinawidz.homestocktracker.presentation.ui.common.Category

data class AddNewItemState(
    val newItem: NewItem = NewItem(),
    val isLoading: Boolean = false,
    val savingState: SavingState = SavingState(),
    val addNewItemError: AddNewItemError = AddNewItemError()
)

data class NewItem(
    val name: String? = null,
    val quantity: Long? = null,
    val category: Category = Category.UNKNOWN
)

data class SavingState(
    val isSaved: Boolean = false,
    val isSavingError:Boolean = false
)

data class AddNewItemError(
    val isNameError: Boolean = false,
    val isQuantityError: Boolean = false
)
