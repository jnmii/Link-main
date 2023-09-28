package com.example.link2.presentation.screens.a_common_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun ButtonLoginRegister(
    onClick: () -> Unit,
    modifier: Modifier,
    text: String,
    shape: Shape,
    colors: ButtonColors,
    elevation: ButtonElevation?,
    fontWeight: FontWeight,
    fontSize: TextUnit

)
{
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = colors,
        elevation = elevation
    ) {
        Text(
            text = text, fontWeight = fontWeight, fontSize = fontSize
        )
    }
}
