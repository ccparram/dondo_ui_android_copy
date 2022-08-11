package com.dondo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dondo.components.ButtonType.Borderless
import com.dondo.components.ButtonType.Primary
import com.dondo.ui.utils.theme.PreviewContainer
import com.dondo.ui.utils.theme.darkBorder

@Composable
fun DondoDualButton(
    modifier: Modifier = Modifier,
    cancelLabel: String,
    actionLabel: String,
    enabled: Boolean = true,
    onCancelClick: () -> Unit,
    onActionClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(75.dp)
            .darkBorder()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DondoButton(
            text = cancelLabel,
            isFromDualButton = true,
            buttonType = Borderless
        ) {
            onCancelClick()
        }
        DondoButton(
            text = actionLabel,
            isFromDualButton = true,
            buttonType = Primary,
            enabled = enabled
        ) {
            onActionClick()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun PrimaryDualButtonPreview() {
    PreviewContainer {
        DondoDualButton(
            cancelLabel = "Cancelar",
            actionLabel = "Guardar",
            onCancelClick = {

            },
            onActionClick = {

            },
        )
    }
}

