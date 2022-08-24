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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dondo.ui.utils.Constants.EMPTY
import com.dondo.ui.utils.theme.DondoThemeContainer
import com.dondo.ui.utils.theme.Gray2
import com.dondo.ui.utils.theme.PreviewContainer
import com.dondo.ui.utils.theme.Shapes

@Composable
fun DondoEditTextField(
    modifier: Modifier = Modifier,
    text: String = EMPTY,
    title: String = EMPTY,
    placeHolder: String = EMPTY,
    isMultiline: Boolean = false,
    enabled: Boolean = true,
    isRequired: Boolean = true,
    showError: Boolean = false,
    errorMessage: String = EMPTY,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyBoardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit
) {
    val isFocused = remember { mutableStateOf(false) }

    Column {
        BasicTextField(
            value = text,
            singleLine = !isMultiline,
            enabled = enabled,
            keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
            keyboardActions = keyBoardActions,
            onValueChange = {
                onValueChange(it)
            },
            modifier = modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 60.dp)
                .background(Color.White)
                .border(
                    width = if (isFocused.value) 2.dp else 1.dp,
                    shape = Shapes.medium,
                    color = textFieldBorderColor(showError, enabled, isRequired)
                )
                .onFocusChanged {
                    isFocused.value = it.isFocused
                },
            decorationBox = { innerTextField ->
                Column(
                    modifier = Modifier.padding(16.dp),
                    content = {
                        Text(text = title, style = DondoThemeContainer.typography.body2.copy(color = Gray2))
                        Spacer(modifier = Modifier.size(2.dp))
                        if (text.isEmpty() && !isFocused.value) {
                            Text(text = placeHolder, style = DondoThemeContainer.typography.body2.copy(color = Gray2))
                        } else {
                            innerTextField()
                        }
                    }
                )
            }
        )
        if (showError) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                textAlign = TextAlign.Start,
                text = errorMessage,
                style = DondoThemeContainer.typography.body1.copy(color = DondoThemeContainer.colors.error),
            )
        }
    }
}

@Composable
private fun textFieldBorderColor(showError: Boolean, enabled: Boolean, isRequired: Boolean) =
    with(DondoThemeContainer.colors) {
        when {
            showError -> error
            !enabled -> backgroundDisabled
            !isRequired -> error
            else -> textSecondary
        }
    }

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun EditTextFieldPreview() {
    PreviewContainer {
        DondoEditTextField(title = "Título del artículo", placeHolder = "Agrega un título a tu artículo") {
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun EditTextFieldDisabledPreview() {
    PreviewContainer {
        DondoEditTextField(
            title = "Título del artículo",
            placeHolder = "Agrega un título a tu artículo",
            enabled = false
        ) {
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun EditTextFieldWithErrorPreview() {
    PreviewContainer {
        DondoEditTextField(
            title = "Título del artículo",
            placeHolder = "Agrega un título a tu artículo",
            showError = true,
            errorMessage = "This is an error",
        ) {
        }
    }
}
