package edu.karolinawidz.homestocktracker.presentation.components.stockitem

sealed class Category {
    data object Food : Category()
    data object Household : Category()
    data object Cosmetics : Category()
    data object Medicine : Category()
    data object Pets : Category()
    data object Car : Category()
    data object Others : Category()
}