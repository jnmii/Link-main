package com.example.link2.presentation.screens.splash_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.link2.R
import com.example.link2.presentation.screens.a_common_components.AppLogo
import com.example.link2.ui.theme.DarkColorScheme
import com.example.link2.ui.theme.Link2Theme

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                DarkColorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        AppLogo(
            logo = painterResource(id = R.drawable.linklogo_2),
            contentDescription = "App Logo",
            modifier = Modifier.size(400.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    Link2Theme {
        SplashScreen()
    }
}
