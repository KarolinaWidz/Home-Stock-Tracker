package edu.karolinawidz.homestocktracker.presentation.components.stockitem

import edu.karolinawidz.homestocktracker.R

sealed class Category(val drawable: Int) {
    data object Food : Category(R.drawable.ic_food)
    data object Household : Category(R.drawable.ic_household)
    data object Cosmetics : Category(R.drawable.ic_cosmetics)
    data object Medicine : Category(R.drawable.ic_medicine)
    data object Pets : Category(R.drawable.ic_pets)
    data object Car : Category(R.drawable.ic_car)
    data object Others : Category(R.drawable.ic_others)
}