package com.example.link2.presentation.screens.register_screen_2.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun AgreeCheckbox(
    isChecked: Boolean, onCheckedChange: (Boolean) -> Unit


)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked, onCheckedChange = onCheckedChange
        )

        Text(text = buildAnnotatedString {
            append("I agree to the ")
            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                append("Terms of Service")
            }
        })
    }
}
