package com.example.link2.presentation.screens.login_screen2

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.link2.R
import com.example.link2.presentation.navigation.Screen
import com.example.link2.presentation.screens.a_common_components.AppLogo
import com.example.link2.presentation.screens.login_screen2.components.SocialLoginButton
import com.example.link2.presentation.screens.a_common_components.CustomTextField
import com.example.link2.presentation.screens.a_common_components.Header
import com.example.link2.presentation.screens.a_common_components.PasswordTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.link2.data.repository.Repository
import com.example.link2.presentation.screens.a_common_components.ButtonLoginRegister
import com.example.link2.presentation.screens.upload_profile_pic.ProfilePictureUpload
import com.example.link2.ui.theme.DarkColorScheme

@Composable
fun LoginScreenPage2(navController: NavController, viewModel: LoginViewModel)
{

    //region State variables
    val email by viewModel.email.collectAsState("")
    val password by viewModel.password.collectAsState("")
    val emailError by viewModel.emailError.collectAsState(null)
    val passwordError by viewModel.passwordError.collectAsState(null)
    //endregion

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

        ) {
            Header(title = "Log In", color = Color.Unspecified)
            Spacer(modifier = Modifier
                .height(35.dp))


            CustomTextField(
                modifier = Modifier.padding(start = 35.dp, end = 35.dp),
                label = "Email",
                value = email,
                onValueChange = viewModel::onEmailChanged,
                keyboardType = KeyboardType.Email,
                leadingIcon = Icons.Default.Email,
                error = emailError ?: "",
            )
            PasswordTextField(
                modifier = Modifier.padding(start = 35.dp, end = 35.dp),
                label = "Password",
                value = password,
                onValueChange = viewModel::onPasswordChanged,
                leadingIcon = Icons.Default.Lock,
//                error = passwordError ?: ""

            )

            ButtonLoginRegister(
                onClick = {
                    viewModel.onLoginClickedUsingLet()
                    navController.navigate(Screen.UploadPPicScreen)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, bottom = 7.dp, start = 35.dp, end = 35.dp)
                    .height(42.dp),
                text = "Log In",
                shape = ButtonDefaults.elevatedShape,
                colors = ButtonDefaults.buttonColors(containerColor = DarkColorScheme.secondary),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 7.dp),
                fontWeight = FontWeight.Normal,
                fontSize = 21.sp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SocialLoginButton(
                    icon = painterResource(id = R.drawable.facebook_2), label = "Facebook"
                ) { /* Handle Facebook login */ }
                SocialLoginButton(
                    icon = painterResource(id = R.drawable.google_2), label = "Google"
                ) { /* Handle Google login */ }
            }

            ClickableText(text = AnnotatedString("Don't have an account? Sign Up"),
                modifier = Modifier.padding(top = 16.dp),
                onClick = { navController.navigate(Screen.RegisterScreen2) })
        }
    }
}

@Preview
@Composable
fun LoginPreview()
{

}
