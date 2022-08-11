package com.dondo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dondo.ui.utils.Constants.EMPTY
import com.dondo.ui.utils.theme.DondoThemeContainer
import com.dondo.ui.utils.theme.PreviewContainer
import com.dondo.ui.utils.theme.Shapes

@Composable
fun DondoEditTextField(
    modifier: Modifier = Modifier,
    title: String = EMPTY,
    placeHolder: String = EMPTY,
    isMultiline: Boolean = false,
    enabled: Boolean = true,
    isRequired: Boolean = false,
    onValueChange: (String) -> Unit
) {
    var value by remember { mutableStateOf(TextFieldValue(EMPTY)) }
    val isFocused = remember { mutableStateOf(false) }

    BasicTextField(
        value = value,
        singleLine = !isMultiline,
        enabled = enabled,
        onValueChange = {
            value = it
            onValueChange(it.text)
        },
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .defaultMinSize(minHeight = 60.dp)
            .padding(horizontal = 18.dp, vertical = 16.dp)
            .border(
                width = if(enabled) 2.dp else 1.dp,
                shape = Shapes.medium,
                color = textFieldBorderColor(enabled, isRequired)
            )
            .onFocusChanged {
                isFocused.value = it.isFocused

                if(!it.isFocused) {

                }
            },
        decorationBox = { innerTextField ->
            Column(
                modifier = modifier.padding(16.dp),
                content = {
                    Text(text = title, style = DondoThemeContainer.typography.body2)
                    Spacer(modifier = Modifier.size(2.dp))
                    if (value.text.isEmpty() && !isFocused.value) {
                        Text(text = placeHolder, style = DondoThemeContainer.typography.body2)
                    } else {
                        innerTextField()
                    }
                }
            )
        }
    )
}

@Composable
private fun textFieldBorderColor(enabled: Boolean, isRequired: Boolean) =
    with(DondoThemeContainer.colors) {
        when {
            !enabled -> backgroundDisabled
            !isRequired -> error
            else -> textSecondary
        }
    }

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun editTextFieldPreview() {
    PreviewContainer {
        DondoEditTextField(title = "Título del artículo", placeHolder = "Agrega un título a tu artículo") {

        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun editTextFieldDisabledPreview() {
    PreviewContainer {
        DondoEditTextField(
            title = "Título del artículo",
            placeHolder = "Agrega un título a tu artículo",
            enabled = false
        ) {

        }
    }
}