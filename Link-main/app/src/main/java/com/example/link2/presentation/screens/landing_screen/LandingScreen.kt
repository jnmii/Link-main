package com.example.link2.presentation.screens.landing_screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.link2.R
import com.example.link2.presentation.navigation.Screen
import com.example.link2.presentation.screens.a_common_components.AppLogo
import com.example.link2.presentation.screens.a_common_components.ButtonLoginRegister
import com.example.link2.ui.theme.BlueKl
import com.example.link2.ui.theme.DarkColorScheme
import com.example.link2.ui.theme.GreenKl
import com.example.link2.ui.theme.OrangeKl
import com.example.link2.ui.theme.PinkKl
import com.example.link2.ui.theme.YellowKl
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LandingScreen(navController: NavController)
{
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        AnimatedBackground() // Add the animated background as the first child
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(top = 217.dp)
        ) {
                AppLogo(
                    logo = painterResource(id = R.drawable.linklogo_2),
                    modifier = Modifier.size(301.dp),
                    contentScale = ContentScale.Fit,
                    contentDescription = "App Logo",

                    )
                Spacer(
                    modifier = Modifier.height(100.dp)
                )

                ButtonLoginRegister(
                    onClick = { navController.navigate(Screen.RegisterScreen2) },
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 14.dp, bottom = 7.dp, start = 35.dp, end = 35.dp)
                        .height(42.dp),
                    text = "Sign Up",
                    shape = ButtonDefaults.elevatedShape,
                    colors = ButtonDefaults.buttonColors(containerColor = DarkColorScheme.secondary),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 7.dp),
                    fontWeight = FontWeight.Normal,
                    fontSize = 21.sp

                )
                val annotatedString = buildAnnotatedString {
                    append("Already have an account?")
                    withStyle(style = SpanStyle(color = DarkColorScheme.secondary)) {
                        append(" Log In")
                    }
                }
                ClickableText(text = annotatedString,
                    modifier = Modifier.padding(top = 17.dp),
                    style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.SemiBold),
                    onClick = { navController.navigate(Screen.LoginScreen) })

            }
        }

    }

@Composable
@Preview
fun LandingScreenPreview()
{
//    LandingScreen()
}

@Composable
fun AnimatedBackground() {
    val colorList = listOf(
        BlueKl.copy(alpha = .2f), // Fully opaque BlueKl
        GreenKl.copy(alpha = .2f), // Fully opaque GreenKl
        YellowKl.copy(alpha = .2f), // Fully opaque YellowKl
        OrangeKl.copy(alpha = .2f), // Fully opaque OrangeKl
        PinkKl.copy(alpha = .2f) // Fully opaque PinkKl
    )
    var colorIndex by remember { mutableStateOf(0) }

    val bgColor by animateColorAsState(targetValue = colorList[colorIndex])

    // Update the colorIndex after each color transition
    LaunchedEffect(bgColor) {
        delay(3000) // Wait for the duration of the color transition
        colorIndex = (colorIndex + 1) % colorList.size // Move to the next color in the list
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
    ) {
        // Other content within the Box
    }
}


