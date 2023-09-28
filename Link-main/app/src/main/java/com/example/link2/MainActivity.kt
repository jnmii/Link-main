package com.example.link2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.link2.data.repository.Repository
import com.example.link2.presentation.navigation.AppNavigator
import com.example.link2.presentation.screens.near_me_screen.NearMeViewModel
import com.example.link2.presentation.screens.upload_profile_pic.ProfilePictureUpload
import com.example.link2.ui.theme.Link2Theme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repository: Repository

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Link2Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavigator(repository)
                }
            }
        }
    }
}
