package com.dondo.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dondo.ui.utils.theme.DondoThemeContainer
import com.dondo.ui.utils.theme.PreviewContainer
import com.dondo.ui.utils.theme.darkBorder

@Composable
fun ChipButton(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean = false,
    onSelectionChanged: (String) -> Unit = {},
) {
    Surface(
        modifier = modifier.padding(4.dp)
            .darkBorder(shape = RoundedCornerShape(10.dp)),
        elevation = if(isSelected) 10.dp else 0.dp,
        shape = RoundedCornerShape(10.dp),
        color = chipStyleColor(isSelected)
    ) {
        Row(
            modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectionChanged(text)
                }
            )
        ) {
            Text(
                text = "#$text",
                style = DondoThemeContainer.typography.body1.copy(color = chipStyleTextColor(isSelected)),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
private fun chipStyleColor(isSelected: Boolean) =
    if (isSelected) {
        DondoThemeContainer.colors.textSecondary
    } else {
        DondoThemeContainer.colors.primary
    }

@Composable
private fun chipStyleTextColor(isSelected: Boolean) = if (isSelected) {
    DondoThemeContainer.colors.textPrimary
} else {
    DondoThemeContainer.colors.textSecondary
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun ChipButtonPreview() {
    PreviewContainer {
        ChipButton(text = "Fotografía") {
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun ChipButtonSelectedPreview() {
    PreviewContainer {
        ChipButton(text = "Fotografía", isSelected = true) {
        }
    }
}
