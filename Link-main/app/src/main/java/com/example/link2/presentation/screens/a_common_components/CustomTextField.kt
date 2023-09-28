package com.example.link2.presentation.screens.a_common_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    leadingIcon: ImageVector? = null,
    error: String? = null,
    icon: ImageVector? = null,
    isEnabled: Boolean? = null,

)
{
    var isUserDoneTyping by remember { mutableStateOf(false) }


    LaunchedEffect(value) {
        if (value.isEmpty())
        {
            return@LaunchedEffect
        }
        isUserDoneTyping = false
        delay(1500L)
        isUserDoneTyping = true

    }
    val shouldDisplayError = isUserDoneTyping

    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            colors = OutlinedTextFieldDefaults.colors(
//                focusedContainerColor = Color(0xFF66CC80),
//                unfocusedContainerColor = Color(0xFF66CC80),
//                focusedBorderColor = Color(0xFF66CC80),
//                unfocusedBorderColor = Color(0xFF66CC80),
            ),
            onValueChange = { onValueChange(it) },
            label = { Text(label) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = true,
            leadingIcon = {
                if (leadingIcon != null)
                {
                    Icon(leadingIcon, contentDescription = null)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                Text(
                    text = if (shouldDisplayError) error.orEmpty() else "",
                    style = TextStyle(
                        color = if (shouldDisplayError) Color.Red else Color.Transparent,
                        fontSize = 12.sp
                    ),
                )
            },
            )

    }

}

