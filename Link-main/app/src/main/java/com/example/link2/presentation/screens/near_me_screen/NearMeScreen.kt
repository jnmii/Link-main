package com.example.link2.presentation.screens.near_me_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.link2.R
import com.example.link2.presentation.screens.a_common_components.UserCard
import com.example.link2.presentation.screens.upload_profile_pic.ProfilePictureViewModel

@Composable
fun NearMeScreen(
    navController: NavController,

) {
    val viewModel = hiltViewModel<NearMeViewModel>()
    val users by viewModel.usersLiveData.observeAsState(emptyList())

    // Trigger the loading of users when the screen is created
    LaunchedEffect(Unit) {
        viewModel.loadUsersWithProfilePictures()
    }

    LazyRow {
        items(users) { user ->
            val painter = painterResource(id = R.drawable.linklogo_2)
            val description = "Some description"
            val title = "Some Title"

            val userPictures = user.profilePictureUrl // Use the user's profilePictureUrl directly

            UserCard(
                user = user,
                painter = painter,
                contentDescription = description,
                title = title,
                profilePictures = userPictures, // Pass the user's profile picture URL here
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp)
            )
        }
    }
}

