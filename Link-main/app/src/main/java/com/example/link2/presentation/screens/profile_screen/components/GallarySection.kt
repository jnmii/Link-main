package com.example.link2.presentation.screens.profile_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.link2.presentation.navigation.Screen
import com.example.link2.presentation.screens.profile_screen.UserProfileViewModel

@Composable
fun GallerySection(navController: NavController, posts: List<String>, viewModel: UserProfileViewModel)
{
    val profilePicturesSnapshot = viewModel.profilePicturesFlow.collectAsState(emptyList()).value
    viewModel.posts = profilePicturesSnapshot

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (posts.size >= 3) 400.dp else if (posts.size <= 2) 200.dp else 0.dp),
        columns = GridCells.Adaptive(minSize = 100.dp)
    ) {
        itemsIndexed(posts) { index, post ->
            Gallery(image = post) {
                viewModel.selectedImageIndex = index
                navController.navigate(Screen.DisplayFullImageScreen)
            }
        }
    }
}

@Composable
private fun Gallery(image: String, onClick: () -> Unit)
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(1.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
    ) {

        Image(
            painter = rememberImagePainter(image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()

        )

    }
}


