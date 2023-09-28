package com.example.link2.presentation.screens.a_common_components

import android.service.autofill.CustomDescription
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AppLogo(
    logo: Painter, modifier: Modifier, contentScale: ContentScale, contentDescription: String
)
{

    Box(contentAlignment = Alignment.Center) {
        Image(
            painter = logo,
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale
        )

    }
}
