package edu.karolinawidz.homestocktracker.presentation.ui.addnewitem

import edu.karolinawidz.homestocktracker.presentation.ui.common.Category

sealed interface AddNewItemIntent {
    data class UpdateName(val name: String) : AddNewItemIntent
    data class UpdateQuantity(val quantity: String) : AddNewItemIntent
    data class UpdateCategory(val category: Category) : AddNewItemIntent
    data object AddItem : AddNewItemIntent
}