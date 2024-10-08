package edu.karolinawidz.homestocktracker.presentation.ui.common

import edu.karolinawidz.homestocktracker.R

enum class Category(val drawable: Int) {
    FOOD(R.drawable.ic_food),
    HOUSEHOLD(R.drawable.ic_household),
    COSMETICS(R.drawable.ic_cosmetics),
    MEDICINE(R.drawable.ic_medicine),
    PETS(R.drawable.ic_pets),
    CAR(R.drawable.ic_car),
    OTHERS(R.drawable.ic_others),
    UNKNOWN(R.drawable.ic_unknown);

    companion object {
        fun findByName(name: String) = Category.entries.firstOrNull { name == it.name } ?: UNKNOWN
        fun getEntries() = Category.entries.filterNot { it == UNKNOWN }
    }
}