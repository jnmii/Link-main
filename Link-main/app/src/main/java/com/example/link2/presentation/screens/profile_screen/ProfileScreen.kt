package com.example.link2.presentation.screens.profile_screen

import androidx.compose.runtime.Composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.link2.presentation.screens.profile_screen.components.DisplayProfilePictures
import com.example.link2.presentation.screens.profile_screen.components.DummyVibesList
import com.example.link2.presentation.screens.profile_screen.components.GallerySection
import com.example.link2.presentation.screens.profile_screen.components.UserInfoSection

@Composable
fun ProfileScreen(navController: NavController, viewModel : UserProfileViewModel)
{

    // Fetch profile pictures for the current user
    viewModel.fetchProfilePicturesForCurrentUser()
    val profilePictures by viewModel.profilePictures.observeAsState(emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            DisplayProfilePictures(profilePictures)
        }

        item {
            UserInfoSection()
        }

        item {
            DummyVibesList()
        }

        item {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Gallery",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = FontFamily.SansSerif,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(15.dp))
                GallerySection(navController,profilePictures, viewModel)
            }
        }
    }
}
