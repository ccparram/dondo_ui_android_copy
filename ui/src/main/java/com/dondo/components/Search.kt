package com.dondo.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dondo.ui.R
import com.dondo.ui.utils.Constants.EMPTY
import com.dondo.ui.utils.theme.Gray1
import com.dondo.ui.utils.theme.Gray2
import com.dondo.ui.utils.theme.PreviewContainer

@Composable
fun SearchBar(
    text: String,
    placeholder: String,
    onTextChange: (String) -> Unit,
    onCloseClick: () -> Unit,
    onSearchClick: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp),
        shape = RoundedCornerShape(32.dp),
        contentColor = Gray1
    ) {
        TextField(
            modifier = Modifier
                .fillMaxSize(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier
                        .fillMaxSize(),
                    text = placeholder,
                    color = Gray2
                )
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = Gray2
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null,
                        tint = Gray2
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange(EMPTY)
                        } else {
                            onCloseClick()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Gray2
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClick(text)
                }
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
fun SearchBarPreview() {
    PreviewContainer {
        SearchBar(
            text = "Camiseta",
            placeholder = "Busca clubes en Dondo",
            onTextChange = {},
            onCloseClick = {},
            onSearchClick = {}
        )
    }
}