package edu.karolinawidz.homestocktracker.presentation.ui.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import edu.karolinawidz.homestocktracker.presentation.ui.common.Category
import edu.karolinawidz.homestocktracker.presentation.ui.theme.CornerRadiusSmall
import edu.karolinawidz.homestocktracker.presentation.ui.theme.PaddingMedium

@Composable
fun CategoryImage(
    category: Category,
    size: Dp,
    modifier: Modifier = Modifier,
    backgroundColor: Color
) {
    Card(
        modifier = modifier
            .padding(horizontal = PaddingMedium)
            .size(size),
        colors = CardDefaults.cardColors().copy(containerColor = backgroundColor),
        shape = RoundedCornerShape(CornerRadiusSmall)
    ) {
        Image(
            modifier = modifier.padding(PaddingMedium),
            painter = painterResource(id = category.drawable),
            contentDescription = category.toString(),
        )
    }
}