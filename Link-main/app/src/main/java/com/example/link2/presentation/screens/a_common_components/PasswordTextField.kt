package com.example.link2.presentation.screens.a_common_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    error: String? = null

)
{
    var isPasswordVisible by remember { mutableStateOf(false) }
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

    OutlinedTextField(value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = modifier
            .fillMaxWidth(),
        leadingIcon = {
            if (leadingIcon != null)
            {
                Icon(leadingIcon, contentDescription = null)
            }
        },
        trailingIcon = {
            val image =
                if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    image,
                    contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                )
            }
        },
        supportingText = {
            Text(
                text = if (shouldDisplayError) error.orEmpty() else "",
                style = TextStyle(
                    color = if (shouldDisplayError) Color.Red else Color.Transparent,
                    fontSize = 12.sp
                ),
            )
        })

}

@Preview
@Composable
fun PreviewPasswordTextField()
{
    PasswordTextField(label = "Password", value = "password123", onValueChange = {})
}
